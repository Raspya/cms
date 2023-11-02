package dev.bernouy.cms.feature.website.page.service;

import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.formatting.response.LayoutFormatting;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.dto.response.PageFormatting;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFormattingPageService {

    public List<PageFormatting> formatPages(List<Page> pages){
        return pages.stream().map(page ->
                        new PageFormatting(
                                page.getId(),
                                page.getName(),
                                page.getTitle(),
                                page.getUrl(),
                                page.getDescription(),
                                page.isPublished(),
                                page.getLayout().getId()))
                .collect(Collectors.toList());
    }
}
