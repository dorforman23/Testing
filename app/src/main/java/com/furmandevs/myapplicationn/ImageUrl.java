package com.furmandevs.myapplicationn;

public class ImageUrl {

    private String url, cat;

    private ImageUrl () {}

    private ImageUrl (String url, String cat) {

        this.url = url;
        this.cat = cat;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

}
