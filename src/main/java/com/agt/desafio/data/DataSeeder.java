/*
package com.agt.desafio.data;

import com.agt.desafio.dto.CriarFuncionarioDTO;
import com.agt.desafio.dto.CriarRegistroViagemDTO;
import com.agt.desafio.dto.CriarVeiculoDTO;
import com.agt.desafio.enumtype.Categoria;
import com.agt.desafio.service.FuncionarioService;
import com.agt.desafio.service.RegistroViagemService;
import com.agt.desafio.service.VeiculoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("dev")
public class DataSeeder {
    @Bean
    CommandLineRunner initDataBase(FuncionarioService funcionarioService, VeiculoService veiculoService, RegistroViagemService registroViagemService) {
        return args -> {
            System.out.println("Iniciando o povoamento do banco de dados com dados de teste...");

            List<CriarFuncionarioDTO> funcionarios = Arrays.asList(
                    new CriarFuncionarioDTO("Felipe Antunes", "felipe.antunes@empresa.com", "63198274501", Categoria.B),
                    new CriarFuncionarioDTO("Helena Costa", "helena.costa@empresa.com", "78965412300", Categoria.A),
                    new CriarFuncionarioDTO("Carlos Santana", "carlos.santana@transporte.com", "47852369011", Categoria.AB),
                    new CriarFuncionarioDTO("Beatriz Almeida", "beatriz.almeida@logistica.com", "52987413629", Categoria.D),
                    new CriarFuncionarioDTO("Ricardo Neves", "ricardo.neves@transportes.com", "69213547822", Categoria.E),
                    new CriarFuncionarioDTO("Juliana Ribeiro", "juliana.ribeiro@empresa.com", "31784529633", Categoria.C),
                    new CriarFuncionarioDTO("Marcos Lima", "marcos.lima@empresa.com", "40827391544", Categoria.A),
                    new CriarFuncionarioDTO("Ana Paula", "ana.paula@logistica.com", "51926384755", Categoria.B),
                    new CriarFuncionarioDTO("Fernando Torres", "fernando.torres@cargas.com", "68234759166", Categoria.D),
                    new CriarFuncionarioDTO("Tatiane Silva", "tatiane.silva@empresa.com", "79451268377", Categoria.E),
                    new CriarFuncionarioDTO("Gabriel Duarte", "gabriel.duarte@transporte.com", "12093475888", Categoria.A),
                    new CriarFuncionarioDTO("Camila Nogueira", "camila.nogueira@empresa.com", "23019486599", Categoria.B),
                    new CriarFuncionarioDTO("João Pedro", "joao.pedro@empresa.com", "34120597610", Categoria.AB),
                    new CriarFuncionarioDTO("Luciana Meireles", "luciana.meireles@cargas.com", "45231608721", Categoria.C),
                    new CriarFuncionarioDTO("Eduardo Farias", "eduardo.farias@transporte.com", "56342719832", Categoria.D),
                    new CriarFuncionarioDTO("Priscila Gomes", "priscila.gomes@logistica.com", "67453820943", Categoria.B),
                    new CriarFuncionarioDTO("Vinícius Rocha", "vinicius.rocha@empresa.com", "78564932054", Categoria.A),
                    new CriarFuncionarioDTO("Renata Castro", "renata.castro@logistica.com", "89675043165", Categoria.AB),
                    new CriarFuncionarioDTO("Thiago Lopes", "thiago.lopes@transporte.com", "90786154276", Categoria.C),
                    new CriarFuncionarioDTO("Larissa Matos", "larissa.matos@empresa.com", "01897265387", Categoria.D),
                    new CriarFuncionarioDTO("Leandro Freitas", "leandro.freitas@cargas.com", "12908376498", Categoria.E),
                    new CriarFuncionarioDTO("Isabela Andrade", "isabela.andrade@empresa.com", "23019487509", Categoria.ACC),
                    new CriarFuncionarioDTO("Rafael Monteiro", "rafael.monteiro@logistica.com", "34120598610", Categoria.B),
                    new CriarFuncionarioDTO("Natalia Barros", "natalia.barros@empresa.com", "45231619721", Categoria.A),
                    new CriarFuncionarioDTO("André Viana", "andre.viana@transporte.com", "56342720932", Categoria.AB),
                    new CriarFuncionarioDTO("Daniela Luz", "daniela.luz@empresa.com", "67453831943", Categoria.C),
                    new CriarFuncionarioDTO("Bruno Teixeira", "bruno.teixeira@logistica.com", "78564943054", Categoria.D),
                    new CriarFuncionarioDTO("Patricia Moura", "patricia.moura@empresa.com", "89675054165", Categoria.E),
                    new CriarFuncionarioDTO("Rodrigo Pinto", "rodrigo.pinto@empresa.com", "90786165276", Categoria.B),
                    new CriarFuncionarioDTO("Vanessa Dias", "vanessa.dias@transporte.com", "01897276387", Categoria.A)
            );

            for (CriarFuncionarioDTO funcionario : funcionarios) {
                funcionarioService.Criar(funcionario);
            }

            List<CriarVeiculoDTO> veiculos = Arrays.asList(
                    new CriarVeiculoDTO("ABC1D23", "Ford", "Fiesta"),
                    new CriarVeiculoDTO("DEF4G56", "Ford", "Focus"),
                    new CriarVeiculoDTO("GHI7J89", "Ford", "Ranger"),
                    new CriarVeiculoDTO("JKL0M12", "Chevrolet", "Onix"),
                    new CriarVeiculoDTO("MNO3P45", "Chevrolet", "Cruze"),
                    new CriarVeiculoDTO("PQR6S78", "Volkswagen", "Gol"),
                    new CriarVeiculoDTO("STU9V01", "Volkswagen", "Polo"),
                    new CriarVeiculoDTO("VWX2Y34", "Fiat", "Uno"),
                    new CriarVeiculoDTO("YZA5B67", "Fiat", "Mobi"),
                    new CriarVeiculoDTO("BCD8E90", "Toyota", "Corolla"),
                    new CriarVeiculoDTO("EFG1H23", "Toyota", "Hilux"),
                    new CriarVeiculoDTO("HIJ4K56", "Honda", "Civic"),
                    new CriarVeiculoDTO("KLM7L89", "Honda", "HR-V"),
                    new CriarVeiculoDTO("NOP0M12", "Hyundai", "HB20"),
                    new CriarVeiculoDTO("QRS3N45", "Hyundai", "Creta"),
                    new CriarVeiculoDTO("TUV6O78", "Renault", "Kwid"),
                    new CriarVeiculoDTO("VWX9P01", "Renault", "Duster"),
                    new CriarVeiculoDTO("XYZ2Q34", "Nissan", "Kicks"),
                    new CriarVeiculoDTO("ABC5R67", "Nissan", "Versa"),
                    new CriarVeiculoDTO("DEF8S90", "Jeep", "Renegade"),
                    new CriarVeiculoDTO("GHI1T23", "Jeep", "Compass"),
                    new CriarVeiculoDTO("JKL4U56", "Ford", "EcoSport"),
                    new CriarVeiculoDTO("MNO7V89", "Chevrolet", "Tracker"),
                    new CriarVeiculoDTO("PQR0W12", "Volkswagen", "Virtus"),
                    new CriarVeiculoDTO("STU3X45", "Volkswagen", "T-Cross"),
                    new CriarVeiculoDTO("VWX6Y78", "Fiat", "Argo"),
                    new CriarVeiculoDTO("YZA9Z01", "Fiat", "Cronos"),
                    new CriarVeiculoDTO("BCD2A34", "Toyota", "Yaris"),
                    new CriarVeiculoDTO("EFG5B67", "Honda", "Fit"),
                    new CriarVeiculoDTO("HIJ8C90", "Hyundai", "Tucson")
            );

            for (CriarVeiculoDTO v : veiculos) {
                veiculoService.Criar(v);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            List<CriarRegistroViagemDTO> viagens = Arrays.asList(
                    new CriarRegistroViagemDTO("ABC1D23", 1L, sdf.parse("2025-07-01 08:30:00"), "São Paulo - SP", "João, Maria"),
                    new CriarRegistroViagemDTO("DEF4G56", 2L, sdf.parse("2025-07-02 09:00:00"), "Campinas - SP", "Carlos, Ana"),
                    new CriarRegistroViagemDTO("GHI7J89", 3L, sdf.parse("2025-07-03 07:45:00"), "Rio de Janeiro - RJ", "Pedro"),
                    new CriarRegistroViagemDTO("JKL0M12", 4L, sdf.parse("2025-07-04 10:15:00"), "Belo Horizonte - MG", ""),
                    new CriarRegistroViagemDTO("MNO3P45", 5L, sdf.parse("2025-07-05 11:00:00"), "Vitória - ES", "Marcos, Julia"),
                    new CriarRegistroViagemDTO("PQR6S78", 6L, sdf.parse("2025-07-06 06:30:00"), "Curitiba - PR", "Roberta"),
                    new CriarRegistroViagemDTO("STU9V01", 7L, sdf.parse("2025-07-07 13:00:00"), "Florianópolis - SC", ""),
                    new CriarRegistroViagemDTO("VWX2Y34", 8L, sdf.parse("2025-07-08 14:20:00"), "Porto Alegre - RS", "Eduardo, Camila"),
                    new CriarRegistroViagemDTO("YZA5B67", 9L, sdf.parse("2025-07-09 12:10:00"), "Brasília - DF", ""),
                    new CriarRegistroViagemDTO("BCD8E90", 10L, sdf.parse("2025-07-10 15:40:00"), "Goiânia - GO", "Fernando"),
                    new CriarRegistroViagemDTO("EFG1H23", 11L, sdf.parse("2025-07-11 16:00:00"), "Salvador - BA", "Vanessa, Leandro"),
                    new CriarRegistroViagemDTO("HIJ4K56", 12L, sdf.parse("2025-07-12 17:30:00"), "Recife - PE", "Tatiane")
            );

            for (CriarRegistroViagemDTO r : viagens) {
                registroViagemService.CriarSaida(r);
            }

            System.out.println("Povoamento do banco de dados de funcionário concluído.");
        };
    }
}
*/
