package com.ui.remote


val inValidResponse = """
   {
    "status": "error",
    "code": "parametersMissing",
    "message": "Required parameters are missing. Please set any of the following parameters and try again: sources, q, language, country, category."
}
    
""".trimIndent()