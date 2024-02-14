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
  
## Reflection
Melalui pengalaman langsung saya dalam menerapkan `CI/CD` di kelas, serta evaluasi mendalam terhadap modul tutorial yang diberikan, saya memiliki keyakinan bahwa alur kerja CI/CD yang saya rancang sudah diimplementasikan dengan efektif. Berikut alasan saya :

1. Setiap perubahan kode yang saya commit dan push ke branch di repositori, langsung dilakukan *suite tes* secara otomatis . Hal ini memastikan kode yang terintegrasi tidak terdapat kesalahan atau error, sehingga mengurangi risiko masalah pada *base code* yang ada.

2. Keberadaan *pipeline deployment* membantu perubahan yang berhasil melewati tes otomatis segera diterapkan ke lingkungan produksi, termasuk penerapan skor dan analisis kualitas kode melalui **SonarCloud**. Selain itu, layanan PaaS seperti **Koyeb** juga terintegrasi ke dalam alur kerja ini, menandakan bahwa implementasi saya mencakup aspek-aspek penting dari *delivery code* hingga ke tahap produksi.

3. Proses *deployment* ke platform PaaS **Koyeb** memungkinkan rilis fitur baru yang lancar tanpa perlu melakukan konfigurasi atau perbaikan yang rumit secara manual.

Semua ini menegaskan bahwa kami telah berhasil mengadopsi prinsip-prinsip CI/CD dengan baik, memastikan bahwa aplikasi kami dapat berkembang secara dinamis dan responsif terhadap perubahan kebutuhan pengembangan serta ekspektasi pengguna.

</details>

