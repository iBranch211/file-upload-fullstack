package spring.files.upload.bootstrap;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.files.upload.service.FileStorageService;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class InitializeStorage implements CommandLineRunner {


    @NonNull FileStorageService storageService;

    @Override
    public void run(String... args) throws Exception {
        //storageService.deleteAll();
        //storageService.init();
    }
}
