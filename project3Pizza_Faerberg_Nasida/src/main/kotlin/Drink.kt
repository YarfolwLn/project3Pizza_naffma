interface Drink {
    fun drinkSale()
    val coffeeSoldCount: Int
    val coffeeRevenue: Double
    val coffeeRefusedCount: Int
    val pizzaWithCoffee: MutableMap<String, Int>
}
