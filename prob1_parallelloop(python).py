import multiprocessing
import os
import time

def compute_sum_of_squares(start, end):
    return sum(i * i for i in range(start, end))

if _name_ == '_main_':
    physical_cores = 4
    logical_cores = os.cpu_count()

    print(f"Detected physical cores: {physical_cores}")
    print(f"Detected logical cores: {logical_cores}")

    n = 100_000_000  
    chunk_size = n 

    ranges = [(i * chunk_size, (i + 1) * chunk_size) for i in range(physical_cores)]
    ranges[-1] = (ranges[-1][0], n)  

    start_time = time.time()

    with multiprocessing.Pool(processes=physical_cores) as pool:
        results = pool.starmap(compute_sum_of_squares, ranges)

    total_sum = sum(results)
    end_time = time.time()

    print(f"Total sum of squares: {total_sum}")
    print(f"Time taken for parallel computation: {end_time - start_time:.2f} seconds")

