package com.bittrex;


import com.bittrex.model.Bot;
import de.elbatya.cryptocoins.bittrexclient.api.model.marketapi.OpenOrder;

public class App {
    public static void main(String[] args) {
        Bot bot = new Bot();
        //bot.printMarkets();
        bot.printOrders();
        OpenOrder btcBntOrder = bot.getBtcBntOrder();
        bot.cancelOrder(btcBntOrder.getOrderUuid());
        bot.printOrders();
    }
}
