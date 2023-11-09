package dev.bernouy.cms.feature.website.library.service;

import dev.bernouy.cms.feature.website.library.Library;
import dev.bernouy.cms.feature.website.library.formatting.response.LibraryFormatting;
import dev.bernouy.cms.feature.website.paramModel.formatting.response.ParamModelFormatting;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFormatingLibraryService {

    public List<LibraryFormatting> formatParamModels(List<Library> libraries){
        return libraries.stream().map(library ->
                        new LibraryFormatting(
                                library.getId(),
                                library.getName()))
                .collect(Collectors.toList());
    }
}
