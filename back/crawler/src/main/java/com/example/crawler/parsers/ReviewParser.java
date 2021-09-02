package com.example.crawler.parsers;

import com.example.common.item.domain.cosmetic.review.Review;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewParser<T> implements Parser<T> {

    private List<Review> reviewList = new ArrayList<>();

    public T parse(Document html){
        reviewList.clear();
        // title
        Elements reviewTitles = html.select("span[class^=totalArea_cate]");
        // content
        Elements reviewContents = html.select("span[class^=totalArea_text]");

        for (int i = 0, size = reviewTitles.size(); i < size; i++){
            Review review = Review.builder()
                    .title(reviewTitles.get(i).html())
                    .content(reviewContents.get(i).html())
                    .build();
            reviewList.add(review);
        }
        return (T)reviewList;
    }
}