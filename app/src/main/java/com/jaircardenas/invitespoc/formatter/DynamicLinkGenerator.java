package com.jaircardenas.invitespoc.formatter;

/**
 * Created by mussio on 26/04/17.
 **/

public class DynamicLinkGenerator {

    public static final String SCHEME_HTTP = "http";
    public static final String SCHEME_HTTPS = "https";

    private String scheme;
    private String domain;
    private String link;
    private String apn;
    private int ad;
    private String al;
    private String afl;
    private String amv;
    private String utm_source;
    private String utm_medium;
    private String utm_campaign;
    private String utm_term;
    private String utm_content;
    private int gclid;

    public DynamicLinkGenerator(String domain, String link, String apn) {
        this.domain = domain;
        this.link = link;
        this.apn = apn;
        this.ad = 0; // 0 = no se muestra en anuncios. 1 = se muestra en anuncios
        this.gclid = -1;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public int getAd() {
        return ad;
    }

    public void setAd(int ad) {
        this.ad = ad;
    }

    public String getAl() {
        return al;
    }

    public void setAl(String al) {
        this.al = al;
    }

    public String getAfl() {
        return afl;
    }

    public void setAfl(String afl) {
        this.afl = afl;
    }

    public String getAmv() {
        return amv;
    }

    public void setAmv(String amv) {
        this.amv = amv;
    }

    public String getUtm_source() {
        return utm_source;
    }

    public void setUtm_source(String utm_source) {
        this.utm_source = utm_source;
    }

    public String getUtm_medium() {
        return utm_medium;
    }

    public void setUtm_medium(String utm_medium) {
        this.utm_medium = utm_medium;
    }

    public String getUtm_campaign() {
        return utm_campaign;
    }

    public void setUtm_campaign(String utm_campaign) {
        this.utm_campaign = utm_campaign;
    }

    public String getUtm_term() {
        return utm_term;
    }

    public void setUtm_term(String utm_term) {
        this.utm_term = utm_term;
    }

    public String getUtm_content() {
        return utm_content;
    }

    public void setUtm_content(String utm_content) {
        this.utm_content = utm_content;
    }

    public int getGclid() {
        return gclid;
    }

    public void setGclid(int gclid) {
        this.gclid = gclid;
    }

    /**
     *
     * Genera en enlace dinámico de Google Firebase
     * @return
     */
    public String getDynamicLink(){

        // template
        // https://example.app.goo.gl/?link=https://www.example.com/someresource&apn=com.example.android&amv=3&al=exampleapp://someresource&ibi=com.example.ios&isi=1234567&ius=exampleapp

        String dl = "";

        // scheme
        dl += scheme  + "://";
        // domain
        dl += domain + "/?";
        // link. deep_link
        dl += "link=" + link + "&";
        // apn. ejemplo: com.example.myappname
        dl += "apn=" + apn + "&";
        // El Dynamic Link se usa en anuncios ? 0 = no, 1 = si
        dl += "ad=" + ad + "&";

        /* parámetros opciones */

        if(al != null && !al.equals("")){
            dl += "al=" + al + "&";
        }

        if(afl != null && !afl.equals("")){
            dl += "afl=" + afl + "&";
        }

        if(amv != null && !amv.equals("")){
            dl += "amv=" + amv + "&";
        }

        if(utm_source != null && !utm_source.equals("")){
            dl += "utm_source=" + utm_source + "&";
        }

        if(utm_medium != null && !utm_medium.equals("")){
            dl += "utm_medium=" + utm_medium + "&";
        }

        if(utm_campaign != null && !utm_campaign.equals("")){
            dl += "utm_campaign=" + utm_campaign + "&";
        }

        if(utm_term != null && !utm_term.equals("")){
            dl += "utm_term=" + utm_term + "&";
        }

        if(utm_content != null && !utm_content.equals("")){
            dl += "utm_content=" + utm_content + "&";
        }

        if( gclid != -1 ){
            dl += "gclid=" + gclid + "&";
        }

        dl = dl.substring(0, dl.length()-1);

        return dl;
    }
}
