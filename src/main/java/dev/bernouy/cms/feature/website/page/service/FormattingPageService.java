package dev.bernouy.cms.feature.website.page.service;

import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.dto.res.ResPageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormattingPageService {

    public List<ResPageDTO> formatPages(List<Page> pages){
        return pages.stream().map(page ->
                        new ResPageDTO(
                                page.getId(),
                                page.getName(),
                                page.getTitle(),
                                page.getPath(),
                                page.getDescription(),
                                page.isPublished(),
                                page.getLayout().getId()))
                .collect(Collectors.toList());
    }

    public ResPageDTO formatPage(Page page){
        return new ResPageDTO(
                page.getId(),
                page.getName(),
                page.getTitle(),
                page.getPath(),
                page.getDescription(),
                page.isPublished(),
                page.getLayout().getId());
    }
}
