package zw.co.cytex.command_agriculture.command_agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.cytex.command_agriculture.command_agriculture.model.Input;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.CropRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.InputsRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 10/02/2020, Mon
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/inputs")
public class InputController {

    @Autowired
    InputsRepository inputsRepository;

    @Autowired
    CropRepository cropRepository;

    @GetMapping
    public ApiResponse<List<Input>> getInputs(){
        return new ApiResponse<>(HttpStatus.OK.value(),"Inputs successfully fetched",inputsRepository.findAll());
    }

    @PostMapping
    public ApiResponse<Input> saveInput(@RequestBody Input input){
        if(inputsRepository.existsByCropId(input.getCropId())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Input already exists",null);
        }
        else{
            if(cropRepository.existsById(input.getCropId())){
                return new ApiResponse<>(HttpStatus.OK.value(),"Input successfully added", inputsRepository.save(input));
            }
            else{
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Crop do not exist",null);
            }
        }
    }

    @PutMapping
    public ApiResponse<Input> updateInput(@RequestBody Input input){
        Optional<Input> inputOptional=inputsRepository.findById(input.getId());
        if(!inputOptional.isPresent()){
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Input do not exists",null);
        }
        else{
            Optional<Input> inputOptional1=inputsRepository.findByCropId(input.getCropId());
            if(inputOptional1.isPresent()) {
                if (input.getId().equals(inputOptional1.get().getId())) {
                    input.setQuantity(input.getQuantity() + inputOptional1.get().getQuantity());
                    return new ApiResponse<>(HttpStatus.OK.value(), "Input successfully added", inputsRepository.save(input));
                }
                else{
                    return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Inputs can not be merged",null);
                }
            }
            else{
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Crop do not exist",null);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Input> deleteInput(@PathVariable Long id){
        if(inputsRepository.existsById(id)){
            inputsRepository.deleteById(id);
            return new ApiResponse<>(HttpStatus.OK.value(),"Input successfully deleted",null);
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Input with that id do dot exists", null);
        }
    }
}
