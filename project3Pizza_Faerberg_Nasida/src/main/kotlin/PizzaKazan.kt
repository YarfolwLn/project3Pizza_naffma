class PizzaKazan(
    neapolitanPizzaPrice: Double, romanPizzaPrice: Double,
    sicilianPizzaPrice: Double, tyroleanPizzaPrice: Double
) : PizzaCity(
    neapolitanPizzaPrice, romanPizzaPrice,
    sicilianPizzaPrice, tyroleanPizzaPrice
), CheckPhoto, Drink {

    override var totalDiscount = 0.0
    override var checkShownCount = 0
    override var checkNotShownCount = 0

    override var coffeeSoldCount = 0
    override var coffeeRevenue = 0.0
    override var coffeeRefusedCount = 0
    override val pizzaWithCoffee = mutableMapOf(
        "Неаполитанская" to 0,
        "Римская" to 0,
        "Сицилийская" to 0,
        "Тирольская" to 0
    )

    private var saucesSold = mutableMapOf(
        "Чесночный" to 0,
        "Барбекю" to 0
    )
    private val saucePrices = mapOf(
        "Чесночный" to 50.0,
        "Барбекю" to 60.0
    )
    private var sauceRevenue = 0.0
    private var currentPizzaType = ""

    override fun showCheckPhoto() {
        println("У вас есть фотография чека?")
        println("1. Да\n2. Нет")
        when (readln()) {
            "1" -> {
                println("Вам будет скидка 50 рублей с покупки")
                totalDiscount += 50.0
                checkShownCount++
            }
            "2" -> {
                checkNotShownCount++
            }
        }
    }

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

    private fun offerSauce() {
        println("Выберите соус к пицце:")
        println("1. Чесночный (50 руб)\n2. Барбекю (60 руб)\n3. Без соуса")
        when (readln()) {
            "1" -> {
                println("Добавлен чесночный соус")
                saucesSold["Чесночный"] = saucesSold["Чесночный"]!! + 1
                sauceRevenue += saucePrices["Чесночный"]!!
            }
            "2" -> {
                println("Добавлен соус барбекю")
                saucesSold["Барбекю"] = saucesSold["Барбекю"]!! + 1
                sauceRevenue += saucePrices["Барбекю"]!!
            }
            "3" -> {
                println("Пицца без соуса")
            }
        }
    }

    override fun neapolitanPizzaSale() {
        neapolitanPizzaCount++
        currentPizzaType = "Неаполитанская"
        println("Спасибо за покупку неаполитанской пиццы в Казани")
        offerSauce()
    }

    override fun romanPizzaSale() {
        romanPizzaCount++
        currentPizzaType = "Римская"
        println("Спасибо за покупку римской пиццы в Казани")
        offerSauce()
    }

    override fun sicilianPizzaSale() {
        sicilianPizzaCount++
        currentPizzaType = "Сицилийская"
        println("Спасибо за покупку сицилийской пиццы в Казани")
        offerSauce()
    }

    override fun tyroleanPizzaSale() {
        tyroleanPizzaCount++
        currentPizzaType = "Тирольская"
        println("Спасибо за покупку тирольской пиццы в Казани")
        offerSauce()
    }

    override fun showStatistics() {
        println("=== Статистика по пиццерии Казань ===")
        println(getPizzaStats())

        val pizzaRevenue = getTotalPizzaRevenue()
        val totalRevenue = pizzaRevenue + coffeeRevenue + sauceRevenue - totalDiscount

        println("Выручка за пиццу: $pizzaRevenue")
        println("Общая сумма скидок: $totalDiscount")
        println("Продано кофе: $coffeeSoldCount")
        println("Выручка за кофе: $coffeeRevenue")

        val totalSauces = saucesSold.values.sum()
        if (totalSauces > 0) {
            println("\nСтатистика по соусам:")
            saucesSold.forEach { (sauceType, count) ->
                if (count > 0) {
                    val revenue = count * saucePrices[sauceType]!!
                    println("$sauceType: $count шт., выручка: $revenue")
                }
            }
            println("Общая выручка за соусы: $sauceRevenue")
        }

        println("Итоговая выручка (с учетом всех услуг и скидок): $totalRevenue")

        val totalChecks = checkShownCount + checkNotShownCount
        if (totalChecks > 0) {
            val shownPercentage = (checkShownCount.toDouble() / totalChecks) * 100
            val notShownPercentage = (checkNotShownCount.toDouble() / totalChecks) * 100
            println("\nСтатистика по чекам:")
            println("Показали чек: $checkShownCount (${"%.2f".format(shownPercentage)}%)")
            println("Не показали чек: $checkNotShownCount (${"%.2f".format(notShownPercentage)}%)")
        }

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
