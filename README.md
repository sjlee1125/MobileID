# Mobile ID

Mobile ID는 아날로그 신분증을 디지털로 옮겨온 어플리케이션입니다. 지문인식 혹은 비밀번호로 본인의 신분증데이터에 접근할 수 있습니다. Hyperledger Fabric Network 망에서 본인의 개인정보를 불러옵니다. 개인정보의 변동이나 삭제, 생성 데이터가 블록체인 네트워크에 저장되어 위변조가 어렵습니다. 



## 개발환경

* Android
* Hyperledger Fabric
* Docker
* Node js
* GCP



## Hyperledger Fabric 네트워크 구성 및 구축

* Hyperledger Fabric 네트워크는 Google Cloud Platform 의 VM인스턴스 기능을 이용하여 5대를 구축하였고
   [[Multi-Host-Fabric-Network](https://github.com/sjlee1125/Multi-Host-Fabric-Network)]에 정리하였다.
* Client SDK는 Node js 를 사용하였으며 Android에서 HTTP 통신으로 요청을 하면 SDK가 개인정보를 수신한다.
  [[hfc-nodejs](https://github.com/sjlee1125/hfc-nodejs)]에 정리하였다.

