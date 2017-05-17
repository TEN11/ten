package com.example.ayou.ten.bean;

/**
 * Created by AYOU on 2017/5/17.
 */

public class PicContentBean {


    /**
     * id : 100278
     * title : 生的学习
     * author : Brice Portolano
     * authorbrief : 摄影©Brice Portolano
     * text1 : 美仍在那儿，并未消失，然而我们不再对它开放胸怀了，它被我们每日单调的生活所吞灭了。 由于我们内心枯萎，忘了何谓仁慈体谅，忘了如何观看星辰、树木、水中倒影，因此我们需要借着图画、珠宝、书本以及无穷无尽的娱乐以获得刺激。我们不断地寻求新的刺激，新的战栗情绪，我们渴求日益繁多的感官刺激。这项渴求与它所获得的满足，造成心智和情感的厌倦与无聊。只要我们寻求的是感官刺激，被我们称之为美或丑的事物便只有极肤浅……
     * image1 : images/BE379637D0739E4E659B378D99ED2E2F.png
     * text2 : —克里希那穆提《一生的学习》
     * times : 3748
     * publishtime : 636305760000000000
     * status : 0
     * errMsg : null
     */

    private int id;
    private String title;
    private String author;
    private String authorbrief;
    private String text1;
    private String image1;
    private String text2;
    private int times;
    private long publishtime;
    private int status;
    private Object errMsg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorbrief() {
        return authorbrief;
    }

    public void setAuthorbrief(String authorbrief) {
        this.authorbrief = authorbrief;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public long getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(long publishtime) {
        this.publishtime = publishtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(Object errMsg) {
        this.errMsg = errMsg;
    }
}
