from locust import *
import random
import json

class Client(TaskSet):

    @task
    def ping(self):
        data = json.dumps({"a": random.randint(1, 100), "b": random.randint(1, 100)})
        self.client.post("http://localhost:8080/sum", data=data, name="Sum", headers={"content-type": "application/json"})


class Client(HttpLocust):
    task_set = Client
    host = "localhost"
    min_wait = 500
    max_wait = 1000
