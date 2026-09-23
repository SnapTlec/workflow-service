package br.com.workflow.dto.Request.Input;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
public class RequestCreateDTO {
    @NotBlank(message = "O título é obrigatório")
    private String title;

    private String description;

    @NotNull(message = "O ID do criador é obrigatório")
    private String createdBy;

    private List<Integer> additionalRequesterIds;
}
