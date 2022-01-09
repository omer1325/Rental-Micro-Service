package com.appsdeveloperblog.rentacar.api.rental.business.concretes;

import com.appsdeveloperblog.rentacar.api.rental.business.abstracts.CarService;
import com.appsdeveloperblog.rentacar.api.rental.business.constants.messages.CarMessages;
import com.appsdeveloperblog.rentacar.api.rental.business.dtos.CarSearchListDto;
import com.appsdeveloperblog.rentacar.api.rental.business.request.carRequests.CreateCarRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.carRequests.DeleteCarRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.carRequests.UpdateCarRequest;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.business.BusinessRules;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.mapping.ModelMapperService;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.ErrorResult;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.Result;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.SuccessResult;
import com.appsdeveloperblog.rentacar.api.rental.dataAccess.abstracts.CarDao;
import com.appsdeveloperblog.rentacar.api.rental.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {

    private CarDao carDao;
    private ModelMapperService modelMapperService;


    @Autowired
    public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
        super();
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<CarSearchListDto> getCars() {

        List<Car> list = this.carDao.findAll();
        List<CarSearchListDto> response = list.stream().map(car -> modelMapperService.forDto()
                .map(car, CarSearchListDto.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public Result save(CreateCarRequest createCarRequest) {
        Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
        car.setCarId(UUID.randomUUID().toString());
        this.carDao.save(car);
        return new SuccessResult(CarMessages.add);
    }

    @Override
    public Result delete(DeleteCarRequest deleteCarRequest) {
        Car car = modelMapperService.forRequest().map(deleteCarRequest, Car.class);
        this.carDao.delete(car);
        return new SuccessResult(CarMessages.delete);
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        Result result = BusinessRules.run(checkExistingCar(updateCarRequest.getCarId()));
        if (result != null) {
            return result;
        }

        Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
        this.carDao.save(car);
        return new SuccessResult(CarMessages.update);
    }
    public Result checkExistingCar(String carId) {
        boolean isExist = carDao.existsById(carId);
        if (!isExist) {
            return new ErrorResult(CarMessages.carNotFound);
        }
        return new SuccessResult();
    }

}
