abstract class PizzaCity(
    protected val neapolitanPizzaPrice: Double,
    protected val romanPizzaPrice: Double,
    protected val sicilianPizzaPrice: Double,
    protected val tyroleanPizzaPrice: Double
) {
    protected var neapolitanPizzaCount = 0
    protected var romanPizzaCount = 0
    protected var sicilianPizzaCount = 0
    protected var tyroleanPizzaCount = 0

    abstract fun neapolitanPizzaSale()
    abstract fun romanPizzaSale()
    abstract fun sicilianPizzaSale()
    abstract fun tyroleanPizzaSale()
    abstract fun showStatistics()

    protected fun getTotalPizzaRevenue(): Double {
        return neapolitanPizzaCount * neapolitanPizzaPrice +
                romanPizzaCount * romanPizzaPrice +
                sicilianPizzaCount * sicilianPizzaPrice +
                tyroleanPizzaCount * tyroleanPizzaPrice
    }

    protected fun getPizzaStats(): String {
        return """
            Продано неаполитанской пиццы: $neapolitanPizzaCount
            Продано римской пиццы: $romanPizzaCount
            Продано сицилийской пиццы: $sicilianPizzaCount
            Продано тирольской пиццы: $tyroleanPizzaCount
        """.trimIndent()
    }
}
