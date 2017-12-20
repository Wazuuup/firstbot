package com.bittrex.model;

import de.elbatya.cryptocoins.bittrexclient.BittrexClient;
import de.elbatya.cryptocoins.bittrexclient.api.model.common.ApiResult;
import de.elbatya.cryptocoins.bittrexclient.api.model.marketapi.OpenOrder;
import de.elbatya.cryptocoins.bittrexclient.api.model.publicapi.Market;
import de.elbatya.cryptocoins.bittrexclient.config.ApiCredentials;

import java.util.List;

public class Bot {
    private BittrexClient bittrexClient;

    public Bot() {
        ApiCredentials apiCredentials = new ApiCredentials("", "");
        bittrexClient = new BittrexClient(apiCredentials);
    }

    public BittrexClient getBittrexClient() {
        return bittrexClient;
    }

    public void setBittrexClient(BittrexClient bittrexClient) {
        this.bittrexClient = bittrexClient;
    }

    public void printMarkets() {
        ApiResult<List<Market>> apiResult = bittrexClient.getPublicApi().getMarkets();
        List<Market> markets = apiResult.unwrap();

        markets.forEach((m) -> System.out.println(printMarket(m)));
    }

    private String printMarket(Market market) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(market.getBaseCurrency()).append(" ");
        stringBuilder.append(market.getMarketCurrency()).append(" ");
        stringBuilder.append(market.getMinTradeSize());
        return stringBuilder.toString();
    }

    public void printOrders() {
        ApiResult<List<OpenOrder>> apiResult = bittrexClient.getMarketApi().getOpenOrders("BTC-BNT");
        List<OpenOrder> openOrders = apiResult.unwrap();

        openOrders.forEach((o) -> System.out.println(printOpenOrder(o)));
    }

    public OpenOrder getBtcBntOrder() {
        ApiResult<List<OpenOrder>> apiResult = bittrexClient.getMarketApi().getOpenOrders("BTC-BNT");
        List<OpenOrder> openOrders = apiResult.unwrap();
        return openOrders.get(0);
    }

    private String printOpenOrder(OpenOrder order) {
        return "- " + order.getExchange() + " " + order.getOrderType() + " " + order.getQuantity() + " " + order.getPrice() + " " + order.getOrderUuid();
    }

    public void cancelOrder(String uuid) {
        bittrexClient.getMarketApi().cancelOrder(uuid);
        System.out.println("Order " + uuid + " cancel requested");
    }
}
