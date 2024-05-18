# Лаба №6. Удаленный вызов процедур с помощью JSON(gRPC) (Windows)
## Необходимые инструменты
Для реализации этой лабы будет необходимо установить 
* [docker](https://docs.docker.com/desktop/install/windows-install/). С этим вам может помочь или [этот](https://www.youtube.com/watch?v=ZyBBv1JmnWQ&ab_channel=TheBinaryBits) видос, или любой другой на просторах ютуба.
* vscode и пара расширений для него(ниже приложу скрин)

### Подробнее про расширения
Основное:
* c/c++ Extension Pack. Как раз для работы с плюсами. +там сразу докачиваются CMake расширения.
* vscode-proto3. Чтоб было комфортнее работать с прото файлом.

Мб понадобятся:
* Docker
* Dev Containers

![Все расширения](https://drive.google.com/uc?export=view&id=1LtVnlYsT1SfW0VEDPCb-S6e30a2GI4GQ)

## Ставим gRPC
Создаём на компе папку, можно на рабочем столе. Открываем эту папку в vscode и создаём в ней **Dockerfile**.
Записываем в него следующее:

```dockerfile
FROM ubuntu:latest

RUN apt-get update && apt-get install -y cmake build-essential git

WORKDIR /deps

RUN git clone --recurse-submodules -b v1.62.0 --depth 1 --shallow-submodules https://github.com/grpc/grpc

RUN mkdir -p /deps/grpc/build && cd /deps/grpc/build && \
    cmake -DgRPC_INSTALL=ON \
      -DgRPC_BUILD_TESTS=OFF \
      .. && \
      make -j8 install
```
  

> _!!! Не забываем сохранять файлы при их изменении !!!_
---

Открываем в vscode терминал и вводим `docker build -t mygrpc .`
Далее начнётся установка, которая может занять около 15-20 минут.
![Процесс установки](https://drive.google.com/uc?export=view&id=1paEmANhL-X77LTC7iiB20NSpGRCJeH1l)

---

После установки пишем в терминале `docker images`. Если у вас в появившейся таблице будет поле с **mygrpc**, то всё гуд и можно двигаться дальше.
  

![Должно быть так](https://drive.google.com/uc?export=view&id=1Mni5R-pJM5R8TsR02aPGoHlALhG7oKI1)

---

Далее прописываем в консоли `docker run -it mygrpc` и жмём на кнопку в левом нижнем углу. Откроется окно, где нужно будет нажать _Attach to Running Container_.
  

![](https://drive.google.com/uc?export=view&id=10nzekakDi9soY60MLDggxS2o0teuuOhp)

После чего vscode вам предложит выбрать контейнер 
  

![](https://drive.google.com/uc?export=view&id=1UD6DErRfbnuCXz90GxXpdj1G73gK4hOi)
  
После выбора контейнера в редакторе откроется новое окно, возможно сразу в папке root, а возможно в папку нужно будет перейти самому.

![](https://drive.google.com/uc?export=view&id=1yFmXN330cCcIdezdYdIhyBKsRMxKrOjC)
1. Тут можно прописать путь(/root)
2. Тут по-идее должны быть какие-то файлы, как на скрине. Если их нет, то мб что-то было сделано не так(но я не уверен, можете перепроверить правильность своих прошлых шагов или попробовать пойти дальше)
  
Далее в root'е создаём папку и открываем её. (В моём случае папка с названием workdir. Полный путь получится /root/workdir)
  
---
Создаём следующую структуру папок: 
  
![dirs struct](https://drive.google.com/uc?export=view&id=1vyu7AxAFn8NOEFh8FAfLNiUqMKwECM_Y)
  
_*Ремарка_:
  
На скрине редактор почему-то говорит об ошибках в файлах, хотя по факту их нет и **быть не должно**. Помогал поднять эту лабу нескольким ребятам, и у всех редактор ни на что не ругался. Подобный казус произошел только при работе над этим "туториалом". Но, при этом, отображение этих ошибок **ни на что не влияло** и дальше всё прекрасно работало. :/
  
---
Копируем в файлы следующий код:
  
* CMakeLists.txt
```cmake
cmake_minimum_required(VERSION 3.20)
project(myserver)


set(CMAKE_EXPORT_COMPILE_COMMANDS ON)
find_package(Protobuf CONFIG REQUIRED)
find_package(gRPC CONFIG REQUIRED)

add_library(protolib proto/test.proto)
target_link_libraries(protolib gRPC::grpc++)
target_include_directories(protolib PUBLIC ${CMAKE_CURRENT_BINARY_DIR})
get_target_property(grpc_cpp_plugin_location gRPC::grpc_cpp_plugin LOCATION)

protobuf_generate(TARGET protolib LANGUAGE cpp)
protobuf_generate(TARGET protolib LANGUAGE grpc
GENERATE_EXTENSIONS .grpc.pb.h .grpc.pb.cc
PLUGIN "protoc-gen-grpc=${grpc_cpp_plugin_location}")


add_executable(server src/server.cpp)
add_executable(client src/client.cpp)
target_link_libraries(server protolib)
target_link_libraries(client protolib)
```
  
* test.proto
```protobuf
syntax = "proto3";

service Greeter {
  rpc SayHello (HelloRequest) returns (HelloReply);
}

message HelloRequest {
  string name = 1;
}

message HelloReply {
  string message = 1;
}
```
  
* server.cpp
```cpp
#include <iostream>
#include <memory>
#include <string>

#include <grpcpp/grpcpp.h>
#include "proto/test.grpc.pb.h"
#include "proto/test.pb.h"


using grpc::Server;
using grpc::ServerBuilder;
using grpc::ServerContext;
using grpc::Status;

bool isInt(std::string str) {

    for (int i = 0; i < str.length(); i++)
        if (!isdigit(str[i]))
            return false;

    return true;
}

// Сортировка
std::string shellSort(std::string string_of_nums)
{
    int* num_array = new int[string_of_nums.length()];
    int j, array_index = 0;
    std::string sub_string;


    // ------------------------------------
    sub_string = string_of_nums[0];
    for (int i = 0; i < string_of_nums.length() - 1; i++) {
        j = i + 1;

        while (string_of_nums[j] != ' ') {

            if (j >= string_of_nums.length())
                break;
            sub_string += string_of_nums[j];
            j++;
        }

        if (isInt(sub_string)) {
            num_array[array_index] = stoi(sub_string);
            array_index++;
        }

        sub_string = "";
        i = j - 1;
    }
    // ----------------------------------------


    // ------------------------------------------

    for (int gap = array_index / 2; gap > 0; gap /= 2)
    {
        for (int i = gap; i < array_index; i++)
        {
            int temp = *(num_array + i);

            int j;
            for (j = i; j >= gap && *(num_array + (j - gap)) > temp; j -= gap)
                *(num_array + j) = *(num_array + (j - gap));

            *(num_array + j) = temp;
        }
    }
    // -------------------------------------


    std::string sort_result = "";

    for (int i = 0; i < array_index; i++) {
        sort_result += (std::to_string(num_array[i]) + " ");
    }

    delete[] num_array;

    return sort_result;
}

class GreeterServiceImpl final : public Greeter::Service {
    Status SayHello(ServerContext* context, const HelloRequest* request, HelloReply* reply) override {
        std::string prefix = "Result: ";
        reply->set_message(prefix + shellSort(request->name()));
        return Status::OK;
    }
};

void RunServer() {
    std::string server_address("0.0.0.0:9999");
    GreeterServiceImpl service;

    ServerBuilder builder;
    builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
    builder.RegisterService(&service);

    std::unique_ptr<Server> server(builder.BuildAndStart());
    std::cout << "Server listening on " << server_address << std::endl;
    server->Wait();
}

int main() {
    RunServer();
    return 0;
}
```
  
* client.cpp
```cpp
#include <iostream>
#include <memory>
#include <string>

#include <grpcpp/grpcpp.h>
#include "proto/test.grpc.pb.h"
#include "proto/test.pb.h"

using grpc::Channel;
using grpc::ClientContext;
using grpc::Status;

class GreeterClient {
public:
    GreeterClient(std::shared_ptr<Channel> channel) : stub_(Greeter::NewStub(channel)) {}

    std::string SayHello(const std::string& name) {
        HelloRequest request;
        request.set_name(name);
        HelloReply reply;
        ClientContext context;

        Status status = stub_->SayHello(&context, request, &reply);

        if (status.ok()) {
            return reply.message();
        } else {
            return "RPC failed";
        }
    }

private:
    std::unique_ptr<Greeter::Stub> stub_;
};

int main() {
    std::string server_address("localhost:9999");
    GreeterClient greeter(grpc::CreateChannel(server_address, grpc::InsecureChannelCredentials()));

    std::string user_string;
    std::cout << "Enter nums for sort:" << std::endl;
    getline(std::cin, user_string);

    std::string reply = greeter.SayHello(user_string);
    std::cout << "Greeter received: \n" << reply << std::endl;

    return 0;
}
```
---
Теперь переходим в терминале в папку build:
`cd build/`
  
Далее прописываем:
`cmake ..` и `make`
  
Если всё гуд и билд прошел успешно, тогда по пути workdir/build/proto у вас появятся cpp/cc файлы.
  
![build dir struct](https://drive.google.com/uc?export=view&id=1ZNS92zFmtp1iABg9kupYg6u3lItOQejR)

---
## Проверка работы  
В терминале пишем `./server`
  
![start server](https://drive.google.com/uc?export=view&id=1rWzj3sbiK-n3LuzBYr-0O0BEWazx1OIb)
  
Сервер запущен. Перейдём к клиенту. Создаём второй терминал. Переходим в директорию build и пишем `./client`
  
![client server](https://drive.google.com/uc?export=view&id=1d57V0kVp9WevVOfX5eHg1EoSfNtD9rjG)
  
Для теста через пробел проставьте несколько случайных чисел и отправьте их на сервер. Если вам вернулась отсортированная последовательность чисел, то поздравляю, лаба сделана. :)

---

## Источники
Основные:
* [Видос](https://www.youtube.com/watch?v=1MuwAZJpqFk&t=429s&ab_channel=PracticalSoftware)
* Немного товарища gpt
  
Также могут быть полезными:
* [Дока](https://github.com/grpc/grpc/blob/v1.62.0/BUILDING.md) по установке gRPC.
* Неплохой [видос](https://www.youtube.com/watch?v=_EqVG-El5z0&t=20s&ab_channel=MerionAcademy), где простым языком объясняется что это вообще такое, этот ваш gRPC. Также там рассказывают про его отличие JSON-RPC.
## От автора
В данном "туториале" выполняется задание с сортировкой чисел, но код можно легко адаптировать под любой другой вариант. Если будут вопросы, можете писать в [VK](https://vk.com/kapitan_kosiak).
