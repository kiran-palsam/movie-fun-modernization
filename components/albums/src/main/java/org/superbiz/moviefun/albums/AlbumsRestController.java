package org.superbiz.moviefun.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.superbiz.moviefun.blobstore.BlobStore;

import java.util.List;


@RestController
public class AlbumsRestController {

    private final AlbumsBean albumsBean;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public AlbumsRestController(AlbumsBean albumsBean) {
        this.albumsBean = albumsBean;
    }

    @PostMapping("/albums/add")
    public String addAlbum(@RequestParam String artist, @RequestParam String title, @RequestParam int year, @RequestParam int rating ) {
        Album album =  new Album(artist,title,year,rating);
        albumsBean.addAlbum(album);
        return "success";
    }

    @GetMapping("/albums/getall")
    public List<Album> getAlbums() {
        return albumsBean.getAlbums();
    }


    @GetMapping("/albums/album/{albumId}")
    public Album getAlbum(@PathVariable long albumId) {

        return albumsBean.find(albumId);
    }

}
