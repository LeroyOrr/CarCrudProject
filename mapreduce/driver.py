from concurrent.futures import ThreadPoolExecutor
from mapreduce.mapper import (
    create_car_mapper,
    get_cars_mapper,
    delete_cars_mapper,
    update_car_mapper
)
from mapreduce.reducer import reduce_results


def reduce_results(results):
    """Reduce step: keep only successful results."""
    return [r for r in results if r is not None]


# def mapreduce_get_car(car_ids):
#     with ThreadPoolExecutor(max_workers=10) as executor:
#         mapped = list(executor.map(create_car_mapper(), car_ids))
#     return reduce_results(mapped)
def mapreduce_get_cars(car_ids, max_workers=10):
    """MapReduce for fetching multiple cars in parallel."""
    with ThreadPoolExecutor(max_workers=max_workers) as executor:
        mapped = list(executor.map(get_cars_mapper, car_ids))
    return reduce_results(mapped)


def mapreduce_delete_cars(car_ids, max_workers=10):
    """MapReduce for deleting multiple cars in parallel."""
    with ThreadPoolExecutor(max_workers=max_workers) as executor:
        mapped = list(executor.map(delete_cars_mapper, car_ids))
    return reduce_results(mapped)


def mapreduce_update_cars(update_pairs, max_workers=10):
    """
    update_pairs = [(car_id, car_data), ...]
    """
    def update_wrapper(pair):
        car_id, car_data = pair
        return update_car_mapper(car_id, car_data)

    with ThreadPoolExecutor(max_workers=max_workers) as executor:
        mapped = list(executor.map(update_wrapper, update_pairs))
    return reduce_results(mapped)


def mapreduce_create_cars(car_data_list, max_workers=10):
    """MapReduce for creating many cars."""
    with ThreadPoolExecutor(max_workers=max_workers) as executor:
        mapped = list(executor.map(create_car_mapper, car_data_list))
    return reduce_results(mapped)

