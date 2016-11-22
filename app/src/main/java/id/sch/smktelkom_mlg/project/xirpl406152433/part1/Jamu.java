package id.sch.smktelkom_mlg.project.xirpl406152433.part1;

import java.io.Serializable;

/**
 * Created by Me on 11/20/2016.
 */

public class Jamu implements Serializable {
    public String name;
    public int bahanpokok;
    public int thumbnail;
    public String deskripsi;
    public String bahan;
    public String cara;

    public Jamu(String name, int bahanpokok, int thumbnail, String deskripsi, String bahan, String cara) {
        this.name = name;
        this.bahanpokok = bahanpokok;
        this.thumbnail = thumbnail;
        this.deskripsi = deskripsi;
        this.bahan = bahan;
        this.cara = cara;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBahanpokok() {
        return bahanpokok;
    }

    public void setBahanpokok(int bahanpokok) {
        this.bahanpokok = bahanpokok;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
