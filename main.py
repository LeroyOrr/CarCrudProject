from controllers.CarController import CarController
from csv import DictReader


def load_cars_from_csv(csv_path):
    cars = []
    with open(csv_path, newline="") as file:
        reader = DictReader(file)
        for row in reader:
            cars.append(row)
    return cars


def main():
    controller = CarController("http://localhost:8080/api")

    cars = load_cars_from_csv("data.csv")
    print(cars)
    print(f"Loaded {len(cars)} cars from CSV")

    for car in cars:
        print(f"Creating car: {car}")
        created = controller.create_car(car)
        print("Created:", created)

    print("\n=== Fetching all cars from API ===")
    all_cars = controller.get_all_cars()
    print(all_cars)


if __name__ == "__main__":
    main()
