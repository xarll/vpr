# XML-RPC Инструкция для Windows (VS)

## Быстрый старт
- Список вариантов [(.pdf)](../XML-RPC_Problems.pdf)
- Шаблон [ТЫК](https://drive.google.com/file/d/1EKvI1MK7Iredsp6ndBFQkcPi6y7zO_Zy/view?usp=sharing) **Обязательно скачайте его!**
- Описание методов библиотеки XML-RPC [ТЫК](http://xmlrpcpp.sourceforge.net/doc/classXmlRpc_1_1XmlRpcServer.html)

## Инструкция
Вам потребуется `Visual Studio`

Проект разделен на 2 части:
- Клиент (для открытия проекта **двойной ЛКМ** по `/client/Client.vcxproj`)
- Сервер (для открытия проекта **двойной ЛКМ** по `/server/Server.vcxproj`)

Сначала следует запускать сервер, а после клиент (хотя, если использовать код ниже, то потребность спадает)

## Пример кода (для варианта №2)

### Client

![демострация](https://user-images.githubusercontent.com/76239707/169651891-2fbedd80-4ab6-477a-b651-028d2700d882.png)

**Не копируйте этот код! Используте шаблон выше!**

```C++
#include "xmlrpc/XmlRpc.h"
#include <conio.h>
#include <iostream>

using namespace XmlRpc;

using namespace std;

int main()
{
	system("chcp 1251");

	int port = 20000;
	string server_addres = "localhost";

	XmlRpcValue result;
	XmlRpcValue Arg;

	string value;
	int k = 0;
	cout << "Введите числа построчно, которые хотите сложить(напишите 'stop' для остановки): " << endl;
	while (true)
	{
		cin >> value;
		if (value != "stop")
		{
			Arg[k] = stoi(value);
			k++;
		}
		else
			break;
	}


	XmlRpcClient client(&server_addres[0], port);
	if (client.execute("127.0.0.1", Arg, result))
	{
		cout << endl << "Результат от сервера:" << result[0];
	}
	else 
	{
		cout << "Подключение не установлено, разрыв соединения" << endl;
	}
	client.close();
}
```

### Server

![демострация](https://user-images.githubusercontent.com/76239707/169651928-e6ba3b81-6d19-4ac4-90ad-2b1bf15f8d6d.png)

**Не копируйте этот код! Используте шаблон выше!**

```C++
#include "xmlrpc/XmlRpc.h"
#include <conio.h>
#include <iostream>

using namespace XmlRpc;
using namespace std;

class Sum : public XmlRpcServerMethod 
{ 
public:
    explicit Sum(XmlRpcServer* server) : XmlRpcServerMethod("127.0.0.1", server) {} 

    void execute(XmlRpcValue& params, XmlRpcValue& result) override 
    {
        cout << "Соединение с клиентом установлено, клиент передал следующие параметры:" << std::endl;
        int sum = 0;
        for (int i = 0; i < params.size(); i++)
        {
            std::cout << params[i] << std::endl;
            sum += (int) params[i];
        }
        result[0] = sum;
    }
};

int main() 
{ 
    system("chcp 1251");
    int port = 20000; 

    XmlRpcServer server;
    server.bindAndListen(port);

    Sum sumValues(&server);
  
    server.work(-1.0);
    server.exit();
    server.shutdown();
}
```

## Возможные ошибки

### Не удается найти средства сборки для v143...
Решение:
Если возникает ошибка (как на скриншоте), то нажимаем `ПКМ по проекту`(в моем случае "client")->`свойства`->`общие` и выбираем в пункте `набор инструментов платформы` - `установленную VS` (**см скриншот**)

![изображение](https://user-images.githubusercontent.com/76239707/169651993-ce509af3-c099-4df0-96a8-71782d515cdc.png)

## Помощь

Если столкнулись с неизвестной ошибкой, пишите в [VK](https://vk.com/jkearnsl)
