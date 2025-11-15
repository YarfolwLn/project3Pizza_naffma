class PizzaPeter(
    neapolitanPizzaPrice: Double, romanPizzaPrice: Double,
    sicilianPizzaPrice: Double, tyroleanPizzaPrice: Double
) : PizzaCity(
    neapolitanPizzaPrice, romanPizzaPrice,
    sicilianPizzaPrice, tyroleanPizzaPrice
), Drink {

    override var coffeeSoldCount = 0
    override var coffeeRevenue = 0.0
    override var coffeeRefusedCount = 0
    override val pizzaWithCoffee = mutableMapOf(
        "Неаполитанская" to 0,
        "Римская" to 0,
        "Сицилийская" to 0,
        "Тирольская" to 0
    )

    private var currentPizzaType = ""

    override fun drinkSale() {
        println("Вы будете кофе?")
        println("1. Да\n2. Нет")
        when (readln()) {
            "1" -> {
                println("С вас 200 рублей за кофе")
                coffeeSoldCount++
                coffeeRevenue += 200.0
                pizzaWithCoffee[currentPizzaType] = pizzaWithCoffee[currentPizzaType]!! + 1
            }
            "2" -> {
                coffeeRefusedCount++
            }
        }
    }

    override fun neapolitanPizzaSale() {
        neapolitanPizzaCount++
        currentPizzaType = "Неаполитанская"
        println("Спасибо за покупку неаполитанской пиццы в Санкт-Петербурге")
    }

    override fun romanPizzaSale() {
        romanPizzaCount++
        currentPizzaType = "Римская"
        println("Спасибо за покупку римской пиццы в Санкт-Петербурге")
    }

    override fun sicilianPizzaSale() {
        sicilianPizzaCount++
        currentPizzaType = "Сицилийская"
        println("Спасибо за покупку сицилийской пиццы в Санкт-Петербурге")
    }

    override fun tyroleanPizzaSale() {
        tyroleanPizzaCount++
        currentPizzaType = "Тирольская"
        println("Спасибо за покупку тирольской пиццы в Санкт-Петербурге")
    }

    override fun showStatistics() {
        println("=== Статистика по пиццерии Санкт-Петербург ===")
        println(getPizzaStats())

        val pizzaRevenue = getTotalPizzaRevenue()
        val totalRevenue = pizzaRevenue + coffeeRevenue

        println("Выручка за пиццу: $pizzaRevenue")
        println("Продано кофе: $coffeeSoldCount")
        println("Выручка за кофе: $coffeeRevenue")
        println("Итоговая выручка: $totalRevenue")

        val totalCoffeeOffers = coffeeSoldCount + coffeeRefusedCount
        if (totalCoffeeOffers > 0) {
            val boughtPercentage = (coffeeSoldCount.toDouble() / totalCoffeeOffers) * 100
            val refusedPercentage = (coffeeRefusedCount.toDouble() / totalCoffeeOffers) * 100
            println("\nСтатистика по кофе:")
            println("Купили кофе: $coffeeSoldCount (${"%.2f".format(boughtPercentage)}%)")
            println("Отказались от кофе: $coffeeRefusedCount (${"%.2f".format(refusedPercentage)}%)")

            println("\nСтатистика по пиццам с кофе:")
            val totalCoffeeWithPizza = coffeeSoldCount
            if (totalCoffeeWithPizza > 0) {
                pizzaWithCoffee.forEach { (pizzaType, count) ->
                    if (count > 0) {
                        val percentage = (count.toDouble() / totalCoffeeWithPizza) * 100
                        println("$pizzaType: $count (${"%.2f".format(percentage)}%)")
                    }
                }
            }
        }
    }
}
