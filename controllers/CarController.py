import requests


class CarController:
    def __init__(self, base_url="http://localhost:8080/api"):
        self.base_url = base_url.rstrip("/")

    def create_car(self, car_data):
        response = requests.post(f"{self.base_url}/cars", json=car_data)
        response.raise_for_status()
        return response.json

    def get_cars(self, car_id):
        response = requests.get(f"{self.base_url}/cars/{car_id}")
        response.raise_for_status()
        return response.json()

    def get_all_cars(self):
        response = requests.get(f"{self.base_url}/allcars")
        response.raise_for_status()
        return response.json()

    def delete_car(self, car_id):
        response = requests.delete(f"{self.base_url}/cars/{car_id}")
        response.raise_for_status()
        return response.json()

    def update_car(self, car_id):
        response = requests.put(f"{self.base_url}/cars/{car_id}")
        response.raise_for_status()
        return {"status": "deleted"}
