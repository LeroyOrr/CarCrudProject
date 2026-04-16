from controllers.CarController import CarController

car_api = CarController()


def create_car_mapper(car_data):
    return car_api.create_car(car_data)


def get_cars_mapper(car_id):
    return car_api.get_cars(car_id)


def get_all_cars_mapper():
    return car_api.get_all_cars()


def delete_cars_mapper(car_id):
    return car_api.delete_car(car_id)


def update_car_mapper(car_id):
    return car_api.update_car(car_id)


