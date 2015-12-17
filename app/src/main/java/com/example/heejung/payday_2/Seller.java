package com.example.heejung.payday_2;


public class Seller {

    private String MarketId ;
    private String MarketStatus ;
    private String Soldout ;
    private String Uid ;
    private String BookId;
    private String UniversityId ;
    private String Quantity ;
    private String Sellprice ;
    private String TradeMethod ;
    private String TradePlace  ;
    private String Description ;
    private String DateAdd  ;

    public String getmMarketId() { return MarketId ; }

    public void setmMarketId (String MarketId ) { this.MarketId  = MarketId ; }

    public String getmMarketStatus () { return MarketStatus ; }

    public void setmMarketStatus (String MarketStatus ) { this.MarketStatus  = MarketStatus ; }

    public String getmSoldout () { return Soldout ; }

    public void setmSoldout (String Soldout ) { this.Soldout  = Soldout ; }

    public String getmUid() { return Uid ; }

    public void setmUid (String Uid ) {
        this.Uid  = Uid ;
    }

    public String getmBookId() {
        return BookId;
    }

    public void setmBookId(String BookId) {
        this.BookId =BookId;
    }

    public String getmUniversityId () {
        return UniversityId ;
    }

    public void setmUniversityId (String UniversityId ) {
        this.UniversityId  = UniversityId ;
    }

    public String getmQuantity () {
        return Quantity ;
    }

    public void setmQuantity (String Quantity ) {
        this.Quantity  = Quantity ;
    }

    public String getmSellprice () {
        return Sellprice ;
    }

    public void setmSellprice (String Sellprice ) {
        this.Sellprice  = Sellprice ;
    }

    public String getmTradeMethod () {
        return  TradeMethod ;
    }

    public void setmTradeMethod (String TradeMethod ) {
        this.TradeMethod  =  TradeMethod ;
    }

    public String getmTradePlace  () {
        return TradePlace  ;
    }

    public void setmTradePlace  (String TradePlace  ) {
        this.TradePlace   = TradePlace  ;
    }

    public String getmDescription  () { return Description ; }

    public void setmDescription (String Description ) {
        this.Description = Description  ;
    }

    public String getmDateAdd  () {
        return DateAdd   ;
    }

    public void setmDateAdd   (String  DateAdd ) {
        this.DateAdd  =  DateAdd  ;
    }

}