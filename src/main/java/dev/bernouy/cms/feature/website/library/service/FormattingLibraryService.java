package dev.bernouy.cms.feature.website.library.service;

import dev.bernouy.cms.feature.website.library.Library;
import dev.bernouy.cms.feature.website.library.dto.response.ResLibraryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormattingLibraryService {

    public List<ResLibraryDTO> formatParamModels(List<Library> libraries){
        return libraries.stream().map(library ->
                        new ResLibraryDTO(
                                library.getId(),
                                library.getName()))
                .collect(Collectors.toList());
    }

    public ResLibraryDTO formatParamModel(Library library){
        return new ResLibraryDTO(
                library.getId(),
                library.getName());
    }
}
