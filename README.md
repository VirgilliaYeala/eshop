# Tutorial Pemrograman Lanjut 
**Nama** : **Virgillia Yeala Prabowo** <br/>
**NPM** : **2206829856** <br/>
**Kelas** : **Advance Programming - A**

## Application 💻
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=irgilliayeala_eshop&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=irgilliayeala_eshop)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=irgilliayeala_eshop&metric=coverage)](https://sonarcloud.io/summary/new_code?id=irgilliayeala_eshop)

### Tautan 🔗
Website [ESHOP](https://eshop-yeala-yela.koyeb.app/)

SonarCLoud [ESHOP](https://sonarcloud.io/summary/new_code?id=irgilliayeala_eshop)

## Reflection 📙
<details>
<summary>Tutorial 1</summary>
  
## Reflection 1
Dalam mengembangkan fitur delete dan edit, saya dengan senang hati melaporkan bahwa implementasi kode saya memenuhi standar clean code yang telah dipelajari sebelumnya. Dari penamaan efisien hingga penanganan kesalahan yang tepat, fokus saya adalah menciptakan kode yang mudah dipahami dan dipelihara.

Namun, selama pengembangan fitur delete, saya menghadapi tantangan terkait integrasi anotasi `@DeleteMapping` pada controller dengan Thymeleaf. Meskipun saya telah menambahkan atribut `th:method="delete"` pada HTML, Thymeleaf sulit mendeteksi metode DELETE karena kebanyakan mendukung hanya GET dan POST.

Setelah penyelidikan lebih lanjut, saya menyadari bahwa Thymeleaf sebenarnya mendukung berbagai metode HTTP, termasuk DELETE. Tantangan yang saya hadapi lebih terkait dengan konfigurasi server dan kontroler Spring. Saya menemukan solusi dengan menambahkan konfigurasi `spring.mvc.hiddenmethod.filter.enabled=true` di berkas `application.properties`, meskipun penting untuk memastikan server dan kontroler mendukung metode DELETE secara benar.

## Reflection 2
Setelah menulis unit test, saya merasa sebagian besar lebih tenang dan percaya diri dengan hasilnya, sekitar 70%. Namun, ada sekitar 30% dari perasaan saya yang masih meragukan bahwa unit test yang sudah saya kerjakan mungkin belum cukup untuk memverifikasi program dengan sempurna. Saya menyadari bahwa tidak ada jumlah unit test yang pasti harus dibuat dalam sebuah kelas, dan walaupun saya telah mempelajari tentang code coverage, memiliki 100% code coverage pun tidak menjamin bahwa tidak akan ada bug atau kesalahan dalam kode. Meskipun demikian, tingkat code coverage yang tinggi tetap merupakan indikator yang baik untuk kualitas kode, meskipun masih perlu dipertimbangkan dengan cermat.

Setelah saya meninjau kode dari file `CreateProductFunctionalTest.java` yang telah saya kerjakan, saya menyadari bahwa ada beberapa bagian yang kurang menerapkan prinsip clean code. Salah satu contohnya adalah adanya duplikasi kode dalam metode `simulation_createProduct_isCorrect()`, di mana logika yang sama diulang beberapa kali. Hal ini dapat menyebabkan kesulitan dalam pemeliharaan kode, karena jika ada perubahan pada setup atau variabel instance, kita harus memperbarui semua tempat di mana kode tersebut diduplikasi. Solusinya mungkin adalah dengan mengekstrak bagian-bagian tersebut ke dalam metode bantuan yang dapat digunakan kembali, sehingga dapat mengurangi duplikasi kode dan membuat kode menjadi lebih bersih dan mudah dipelihara. Dengan cara ini, kita dapat meningkatkan kebersihan dan kualitas kode serta mempermudah pemeliharaan kode di masa mendatang.

</details>

<details>
<summary>Tutorial 2</summary>

## Reflection 1
Selama proses deployment ke branch utama, saya menghadapi masalah dengan kualitas kode yang menyebabkan kesalahan pada aplikasi web setelah di-deploy. Kesalahan yang muncul adalah "WhiteLabel Error Page", yang menunjukkan ada masalah dengan pemetaan controller di aplikasi. Setelah investigasi lebih lanjut melalui log dan event di platform PaaS koyeb.com, saya menemukan bahwa masalah tersebut disebabkan oleh sistem file yang bersifat case-sensitive, yang tidak konsisten dengan penamaan file controller untuk halaman produk dan beranda.

Mengingat pentingnya penamaan file yang konsisten dalam pengembangan aplikasi, terutama ketika bekerja dengan sistem yang case-sensitive, strategi yang saya ambil adalah melakukan normalisasi penamaan file. Saya memastikan bahwa semua referensi ke file dalam kode sumber mengikuti konvensi yang sama dan konsisten dalam penggunaan huruf besar dan kecil. Setelah menyesuaikan penamaan file yang case-sensitive tersebut, saya melakukan commit perubahan ini dan mengepush ulang ke branch utama.
  
## Reflection 2
Melalui pengalaman langsung saya dalam menerapkan `CI/CD` di kelas, serta evaluasi mendalam terhadap modul tutorial yang diberikan, saya memiliki keyakinan bahwa alur kerja CI/CD yang saya rancang sudah diimplementasikan dengan efektif. Berikut alasan saya :

1. Setiap perubahan kode yang saya commit dan push ke branch di repositori, langsung dilakukan *suite tes* secara otomatis . Hal ini memastikan kode yang terintegrasi tidak terdapat kesalahan atau error, sehingga mengurangi risiko masalah pada *base code* yang ada.

2. Keberadaan *pipeline deployment* membantu perubahan yang berhasil melewati tes otomatis segera diterapkan ke lingkungan produksi, termasuk penerapan skor dan analisis kualitas kode melalui **SonarCloud**. Selain itu, layanan PaaS seperti **Koyeb** juga terintegrasi ke dalam alur kerja ini, menandakan bahwa implementasi saya mencakup aspek-aspek penting dari *delivery code* hingga ke tahap produksi.

3. Proses *deployment* ke platform PaaS **Koyeb** memungkinkan rilis fitur baru yang lancar tanpa perlu melakukan konfigurasi atau perbaikan yang rumit secara manual.

Semua ini menegaskan bahwa kami telah berhasil mengadopsi prinsip-prinsip CI/CD dengan baik, memastikan bahwa aplikasi kami dapat berkembang secara dinamis dan responsif terhadap perubahan kebutuhan pengembangan serta ekspektasi pengguna.

</details>

<details>
<summary>Tutorial 3</summary>
  
## Reflection 1
Berikut adalah prinsip yang saya gunakan untuk project eshop saya:
1. **SRP (Single Responsibility Principle)** : Setiap kelas harusnya memiliki 1 fungsionalitas saja. Oleh karena itu, saya menerapkan prinsip ini untuk memisahkan `CarController` dan `ProductController` di file yang berbeda.
2. **OCP (Open/Closed Principle)** : Setiap modul harus terbuka untuk ekstensi, tetapi tertutup oleh modifikasi. Oleh karena itu, saya membuat class interface baru untuk `CarRepository` dan `ProductRepository` agar dapat memenuhi prinsip tersebut. 
3. **ISP (Interface Segregation Principle)** : Interface tidak bisa memaksa sebuah class untuk ngeimplementasi apa yang tidak bisa lakukannya. Oleh karena itu, saya membuat class `ServiceManager` untuk mengumpulkan implementasi antara `CarService` dan `ProductService`yang mirip dan memisahkan implementasi yang merupakan ciri khas baik dari `CarService` maupun `ProductService`.
4. **DIP (Dependency Inversion Principle)** : Setiap komponen harus bergantung pada implementasi yang abstrak bukan pada implementasi konkret. Oleh karena itu saya mengubah atribut yang ada di `CarController` yang tadinya menggunakan `CarServiceImpl` menjadi `CarService`.

## Reflection 2
Keuntungan dari penerapan prinsip SOLID pada project eshop saya:
1. Dengen memenuhi prinsip DIP, project saya terjadi **peningkatan reusabilitas** yang lebih besar, yang artinya komponen-komponennya dapat digunakan kembali lebih mudah karena ketergantungan pada abstraksi daripada implementasi, Contoh:
Mengubah atribut yang ada di `CarController` yang tadinya menggunakan `CarServiceImpl` menjadi `CarService`
```java
...
@Controller
@RequestMapping("/car")
class CarController {

    private CarService carservice;
    private ServiceManager<Car> service;

    public CarController(ServiceManager<Car> service, CarService carservice) {
        this.service = service;
        this.carservice = carservice;
    }
}
...
```
2. Dengan memenuhi prinsip ISP, saya dapat dengan **mudah melakukan pemeliharaan** pada project saya.Dengan setiap antarmuka hanya berisi metode yang spesifik untuk kelas-kelas yang relevan, perubahan pada satu antarmuka tidak akan mempengaruhi kelas-kelas lain yang tidak membutuhkan metode tersebut, Contoh: Membuat class `ServiceManager` untuk mengumpulkan implementasi antara `CarService` dan `ProductService`yang mirip dan memisahkan implementasi yang merupakan ciri khas baik dari `CarService` maupun `ProductService`. Berikut `ServiceManager.java `:
```java
package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface ServiceManager<T> {
    T create(T entity);
    List<T> findAll();
    T findById(String id);
    void deleteById(String id);
}
```
## Reflection 3
Keriguan yang akan saya alami jika saya tidak menerapkan prinsip SOLID dalam project eshop saya:
1. Tanpa memenuhi prinsip ISP, saya mungkin terpaksa untuk bergantung pada antarmuka yang tidak seharusnya saya gunakan, yang mengakibatkan ketergantungan yang tidak perlu dan kompleksitas yang meningkat, Contoh:
```java
package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface ServiceManager<T> {
    T create(T entity);
    List<T> findAll();
    T findById(String id);
    void deleteById(String id);
    // relevan untuk Car, tidak cukup relevan untuk Product
    void drive(String id);
}
```
2. Tanpa memenuhi prinsip SRP, saya akan kesulitan dalam memahami apa yang sebenarnya dilakukan oleh kelas tersebut dan kesulitan melihat perubahan kode pada project saya, karena setiap kelas memiliki tanggung jawab yang beragam, membuat perubahan pada salah satu tanggung jawab dapat memengaruhi fungsionalitas lainnya, Contoh:
Didalam file `ProductController.java`
```java
...
@Controller
@RequestMapping("/product")
public class ProductController {
  private ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }
}
...
// posisi ada dibawah productcontroller membuat saya sulit mencarinya
@Controller
@RequestMapping("/car")
class CarController extends ProductController {

  public CarController(ProductService service) {
    super(service);
  }
}
```
</details>

