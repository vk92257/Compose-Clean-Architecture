package com.ui.domain.useCases

import com.ui.domain.repository.NewBreezeRepository
import javax.inject.Inject

class GetBreakingNewsUseCase @Inject constructor(
    private val newsRepository: NewBreezeRepository
) {

    suspend operator fun invoke(
        country: String,
        api: String
    ) = newsRepository.getBreakingNews(api = api, country = country)


}