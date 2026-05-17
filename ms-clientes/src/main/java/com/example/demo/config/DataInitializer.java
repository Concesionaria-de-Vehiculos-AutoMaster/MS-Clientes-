package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {

        if (clienteRepository.count() == 0) {

            Cliente cliente1 = new Cliente();
            cliente1.setRut("19876543");
            cliente1.setNombre("Cristofer");
            cliente1.setApellido("Figueroa");
            cliente1.setEdad(20);
            cliente1.setCorreo("Cristofer.Figueroa@gmail.com");
            cliente1.setTelefono("987654321");

            Cliente cliente2 = new Cliente();
            cliente2.setRut("20123456");
            cliente2.setNombre("Rolando");
            cliente2.setApellido("Castro");
            cliente2.setEdad(19);
            cliente2.setCorreo("rol.Castro@gmail.com");
            cliente2.setTelefono("912345678");

            Cliente cliente3 = new Cliente();
            cliente3.setRut("11111111");
            cliente3.setNombre("Juan");
            cliente3.setApellido("Topo");
            cliente3.setEdad(99);
            cliente3.setCorreo("Juan.topo@gmail.com");
            cliente3.setTelefono("12345678");

            clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));

            log.info("Datos de prueba cargados en la base de datos");
        } else {
            log.info("La base de datos ya tiene clientes");
        }
    }
}
