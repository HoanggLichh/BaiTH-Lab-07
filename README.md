# Lab 07 – Iterator cho CompositeShow (DesignBook chương 6, Exercise 6 và 7)

## Cấu trúc
```
src/main/java/show/   Show, Movie, Concert (từ DesignBook), CompositeShow (bài 6.1, tự viết lại)
src/main/java/ex6/    CompositeShow2 (Iterable<Show>, KHÔNG đổi Show), Ex6Demo
src/main/java/ex7/    Show2 extends Iterable<Show2>, Movie2, Concert2, CompositeShow3, Ex7Demo
src/test/java/        TestCompositeShow2 (10 test), TestCompositeShow3 (9 test), JUnit 5
results/              demo_output.txt, test_run_output.txt, experiments.txt
```

## Chạy
```
mvn test
mvn -q compile exec:java -Dexec.mainClass=ex6.Ex6Demo   # nếu có exec plugin; hoặc chạy main trong IntelliJ
```

## Ghi chú
- Ex 6: chỉ `CompositeShow2` là `Iterable<Show>`; client phải `instanceof` trước khi mở gói.
- Ex 7: `Show2 extends Iterable<Show2>`; lá trả về iterator rỗng; client không cần `instanceof`.
- `copy()` của lời giải mẫu trả về `new CompositeShow(...)` làm bản sao mất khả năng duyệt; ở đây trả về `CompositeShow2`.
- Kết quả trong `results/` chạy bằng bộ chạy nhỏ với API JUnit giả (môi trường làm bài không tải được JUnit 5). Hãy chạy lại bằng Maven/IntelliJ một lần để xác nhận.
- Mã Show/Movie/Concert thuộc DesignBook (M. Robillard, CC BY-NC-ND 4.0), dùng cho mục đích học tập.
