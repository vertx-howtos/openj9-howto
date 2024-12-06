from locust import *
import random
import json

class Client(HttpUser):
    wait_time = between(0.5, 1)
    host = "http://localhost:8080"

    @task
    def sum(self):
        data = json.dumps({"a": random.randint(1, 100), "b": random.randint(1, 100)})
        self.client.post("/sum", data=data, name="Sum", headers={"content-type": "application/json"})
