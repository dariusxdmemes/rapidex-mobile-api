package com.rapidex.rapidex_mobile_api.controllers.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/rapidex")
class RapidexWebDashController {

    @GetMapping
    fun index(): String {

        return "index"
    }
}