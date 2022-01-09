package com.appsdeveloperblog.rentacar.api.rental.controllers;

import com.appsdeveloperblog.rentacar.api.rental.business.abstracts.CarService;
import com.appsdeveloperblog.rentacar.api.rental.business.dtos.CarDetailDto;
import com.appsdeveloperblog.rentacar.api.rental.business.dtos.CarSearchListDto;
import com.appsdeveloperblog.rentacar.api.rental.business.request.carRequests.CreateCarRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.carRequests.DeleteCarRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.carRequests.UpdateCarRequest;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.DataResult;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cars")
public class CarsController {

    private CarService carService;

    @Autowired
    public CarsController(CarService carService) {
        super();
        this.carService = carService;
    }

    @GetMapping("getAll")
    public List<CarSearchListDto> getCars() {
        return this.carService.getCars();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
        return this.carService.save(createCarRequest);
    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) {
        return this.carService.delete(deleteCarRequest);
    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
        return this.carService.update(updateCarRequest);
    }

}
