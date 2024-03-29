package com.ui.presentation.screen.saved

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ui.data.data.dto.newBreeze.Article
import com.ui.domain.useCases.ArticleCacheUseCase
import com.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

/**
 * @Author: Vivek
 * @Date: 27/02/22
 */

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SavedViewModel @Inject constructor(
    private val newBreezeCache: ArticleCacheUseCase
) : ViewModel() {
    var state by mutableStateOf(SavedState())
        private set
    private val _UiEvent = Channel<UiEvent>()
    val uiEvent = _UiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            newBreezeCache.getArticle().collectLatest {
                val list = mutableStateListOf<Article>().apply {
                    addAll(it)
                }
                state = state.copy(articles = list)
                state.articlesTemp = list
                state.groupedByDate = list.groupBy {/* Util.extractDate*/
                    /* Util.getFormattedDate(
                         smsTimeInMilis = LocalDate.parse(
                             it.publishedAt?.take(10) ?: ""
                         ).toEpochDay()

                     )*/
                    val time = LocalDate.parse(
                        it.publishedAt?.take(10) ?: ""
                    )
                    "${time.dayOfMonth} ${time.month} ${time.year}"

                }

            }
        }

    }

    fun onActionPerformed(action: SavedScreenEvents) {
        when (action) {

            is SavedScreenEvents.OnSearchEvent -> {
                state.articlesTemp.filter { article ->
                    article.title.contains(action.text, true) || article.description?.contains(
                        action.text,
                        true
                    ) ?: false
                }.let { it ->
                    state = state.copy(articles = it.toMutableStateList())
                    state.groupedByDate = state.articles.groupBy {
                        val time = LocalDate.parse(
                            it.publishedAt?.take(10) ?: ""
                        )
                        "${time.dayOfMonth} ${time.month} ${time.year}"

                    }
                }
            }
            is SavedScreenEvents.OnArticleReadEvent -> {
                viewModelScope.launch {
                    _UiEvent.send(UiEvent.ReadArticleClick(state.articles[action.articlePosition]))
                }
            }
            else -> {}
        }
    }


    fun onBackClick() {
        viewModelScope.launch {
            _UiEvent.send(UiEvent.NavigateUp)
        }
    }

}



