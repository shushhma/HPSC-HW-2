import os
import time

physical_cores = os.cpu_count() // 2  
logical_cores = os.cpu_count()

print(f"Python detected: {physical_cores} physical cores.")
print(f"Python detected: {logical_cores} logical cores.")

def serial_task(n):
    result = 0
    for i in range(n):
        result += i * i
    return result

start_time = time.time()

serial_result = serial_task(100000000)

end_time = time.time()

print(f"Serial result: {serial_result}")
print(f"Time taken in serial loop: {end_time - start_time} seconds")
