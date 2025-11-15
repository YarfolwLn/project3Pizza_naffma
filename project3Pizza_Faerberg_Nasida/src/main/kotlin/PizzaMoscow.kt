class PizzaMoscow(
    neapolitanPizzaPrice: Double, romanPizzaPrice: Double,
    sicilianPizzaPrice: Double, tyroleanPizzaPrice: Double
) : PizzaCity(
    neapolitanPizzaPrice, romanPizzaPrice,
    sicilianPizzaPrice, tyroleanPizzaPrice
), CheckPhoto {

    override var totalDiscount = 0.0
    override var checkShownCount = 0
    override var checkNotShownCount = 0

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

    override fun neapolitanPizzaSale() {
        neapolitanPizzaCount++
        println("Спасибо за покупку неаполитанской пиццы в Москве")
    }

    override fun romanPizzaSale() {
        romanPizzaCount++
        println("Спасибо за покупку римской пиццы в Москве")
    }

    override fun sicilianPizzaSale() {
        sicilianPizzaCount++
        println("Спасибо за покупку сицилийской пиццы в Москве")
    }

    override fun tyroleanPizzaSale() {
        tyroleanPizzaCount++
        println("Спасибо за покупку тирольской пиццы в Москве")
    }

    override fun showStatistics() {
        println("=== Статистика по пиццерии Москва ===")
        println(getPizzaStats())

        val pizzaRevenue = getTotalPizzaRevenue()
        val totalRevenue = pizzaRevenue - totalDiscount

        println("Выручка за пиццу: $pizzaRevenue")
        println("Общая сумма скидок: $totalDiscount")
        println("Итоговая выручка (с учетом скидок): $totalRevenue")

        // Статистика по чекам
        val totalChecks = checkShownCount + checkNotShownCount
        if (totalChecks > 0) {
            val shownPercentage = (checkShownCount.toDouble() / totalChecks) * 100
            val notShownPercentage = (checkNotShownCount.toDouble() / totalChecks) * 100
            println("\nСтатистика по чекам:")
            println("Показали чек: $checkShownCount (${"%.2f".format(shownPercentage)}%)")
            println("Не показали чек: $checkNotShownCount (${"%.2f".format(notShownPercentage)}%)")
        }
    }
}
