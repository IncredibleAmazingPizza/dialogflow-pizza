package com.github.omarmiatello.actions

import com.github.omarmiatello.actions.ktx.addSimpleResponse
import com.github.omarmiatello.actions.utils.EasyDialogflowApp
import com.google.actions.api.ActionRequest
import com.google.actions.api.ForIntent

class MyActionsApp : EasyDialogflowApp() {
    @ForIntent("Default Welcome Intent")
    fun welcome(request: ActionRequest) = request.toResponse {
        response.addSimpleResponse(getString(if (request.user.isFirstAccess) "welcome" else "welcome_back"))
    }

    @ForIntent("bye")
    fun bye(request: ActionRequest) = request.toResponse {
        response.addSimpleResponse(getString("bye"))
                .endConversation()
    }

    @ForIntent("Ask Ingredients")
    fun askIngredients(request: ActionRequest) = request.toResponse {
        val pizzaName = request.getParameter("PizzaName")
        val ingredients = when (pizzaName) {
            "Margherita" -> "tomato, mozzarella, fresh basil, salt and oil"
            "Marinara" -> "tomato, garlic, oregano and oil"
            "Prosciutto" -> "tomato, mozzarella, Italian cooked ham"
            "Capricciosa" -> "tomato, mozzarella, Italian cooked ham, mushrooms, artichoke"
            else -> "Sorry, I don't know this pizza. Do you want to try another one?"
        }
        response.addSimpleResponse(ingredients)
    }
}
