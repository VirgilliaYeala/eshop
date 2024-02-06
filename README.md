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
