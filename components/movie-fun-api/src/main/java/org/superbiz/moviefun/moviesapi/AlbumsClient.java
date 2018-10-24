package org.superbiz.moviefun.moviesapi;

import org.apache.tika.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import org.superbiz.moviefun.blobstore.Blob;

import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static java.lang.String.format;
import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {

    private String albumsUrl;
    private RestOperations restOperations;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static ParameterizedTypeReference<List<MovieInfo>> movieListType = new ParameterizedTypeReference<List<MovieInfo>>() {
    };

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo albumInfo) {
        String addAlbumurl = albumsUrl+"/add";
        logger.info("addAlbumurl: "+ addAlbumurl);
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
        parts.add("artist", albumInfo.getArtist());
        parts.add("title", albumInfo.getTitle());
        parts.add("year", albumInfo.getYear());
        parts.add("rating", albumInfo.getRating());

        restOperations.postForEntity(addAlbumurl, parts, String.class);
    }

    public AlbumInfo find(long id) {
        AlbumInfo albumInfo = restOperations.getForObject(albumsUrl+"/album/"+id, AlbumInfo.class);
        logger.info("albumInfo: "+ albumInfo);
        return albumInfo;
    }

    public List<AlbumInfo> getAlbums() {
        String getAllAlbumurl = albumsUrl+"/getall";
        logger.info("getAllAlbumurl: "+ getAllAlbumurl);
        AlbumInfo[] albumInfoArray =  restOperations.getForObject(getAllAlbumurl, AlbumInfo[].class);
        return Arrays.asList(albumInfoArray);
    }


    public void deleteAlbum(AlbumInfo album) {


    }

    public void updateAlbum(AlbumInfo album) {


    }

 /*   public String index(Map<String, Object> model) {
        return restOperations.getForObject(albumsUrl, String.class);
    }

    public String details(long albumId, Map<String, Object> model) {
        return restOperations.getForObject(albumsUrl+"/"+albumId, String.class);
    }

     public String uploadCover(Long albumId, MultipartFile uploadedFile) {

        restOperations.postForEntity(albumsUrl+"/"+albumId+"/cover",uploadedFile, String.class);
        return restOperations.getForObject(albumsUrl+"/"+albumId, String.class);
    }

    public byte[] getCover(@PathVariable long albumId) throws IOException, URISyntaxException {

        return restOperations.getForObject(albumsUrl+"/"+albumId+"/cover", byte[].class);
    }*/
}
