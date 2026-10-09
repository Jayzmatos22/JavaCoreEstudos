package groupingby;

import modelo.BancoEnum;
import modelo.Usuario;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Série de groupingBy com usuários e bancos: counting, mapping, joining,
 * summarizingDouble, maxBy, filtering e agrupamento em dois níveis.
 */
public class GroupingByUsuarios {

    public static void main(String[] args) {
        List<Usuario> usuarios = Usuario.amostra();

        // quantos usuários por banco
        Map<BancoEnum, Long> porBanco = usuarios.stream()
                .collect(groupingBy(Usuario::getBanco, Collectors.counting()));
        System.out.println("Por banco: " + porBanco);

        // emails por banco
        Map<BancoEnum, List<String>> agrupadoEmails = usuarios.stream()
                .collect(groupingBy(Usuario::getBanco,
                        Collectors.mapping(Usuario::getEmail, Collectors.toList())));
        System.out.println("Emails por banco: " + agrupadoEmails);

        // estatísticas por faixa de saldo
        Map<String, DoubleSummaryStatistics> faixa = usuarios.stream()
                .collect(groupingBy(u -> {
                    if (u.getSaldo() > 3000) return "alto";
                    if (u.getSaldo() > 1000) return "médio";
                    return "baixo";
                }, Collectors.summarizingDouble(Usuario::getSaldo)));
        System.out.println("Faixas: " + faixa);
        System.out.println("Média da faixa baixa: " + faixa.get("baixo").getAverage());

        // saldos agrupados pelo nome do banco (chave String, não o enum)
        Map<String, List<Long>> saldosPorNomeBanco = usuarios.stream()
                .collect(groupingBy(u -> u.getBanco().getNome(),
                        Collectors.mapping(Usuario::getSaldo, Collectors.toList())));
        System.out.println("Saldos por nome do banco: " + saldosPorNomeBanco);

        // primeira letra do nome — chave Character
        Map<Character, List<Usuario>> pLetra = usuarios.stream()
                .collect(groupingBy(u -> u.getNome().charAt(0)));
        System.out.println("Por letra inicial: " + pLetra);

        Map<Character, Long> quantosPorLetraInicial = usuarios.stream()
                .collect(groupingBy(u -> u.getNome().charAt(0), Collectors.counting()));
        System.out.println("Quantos por letra: " + quantosPorLetraInicial);

        // emails por banco numa String só
        Map<BancoEnum, String> emailPorBanco = usuarios.stream()
                .collect(groupingBy(Usuario::getBanco,
                        Collectors.mapping(Usuario::getEmail, Collectors.joining(";"))));
        System.out.println("Emails juntos: " + emailPorBanco);

        // maior saldo de cada banco
        Map<BancoEnum, Optional<Usuario>> maiorSaldoPorBanco = usuarios.stream()
                .collect(groupingBy(Usuario::getBanco,
                        Collectors.maxBy(Comparator.comparingDouble(Usuario::getSaldo))));
        System.out.println("Maior saldo por banco: " + maiorSaldoPorBanco);

        // filtering DENTRO do groupingBy: o banco aparece mesmo com a lista vazia
        Map<BancoEnum, List<Usuario>> porBancoSaldo500Mais = usuarios.stream()
                .collect(groupingBy(Usuario::getBanco,
                        Collectors.filtering(u -> u.getSaldo() > 500, Collectors.toList())));
        System.out.println("filtering (> 500): " + porBancoSaldo500Mais);

        // filter ANTES: o banco sem ninguém acima de 500 some do mapa
        Map<BancoEnum, List<Usuario>> porBancoSaldo500Mais2 = usuarios.stream()
                .filter(u -> u.getSaldo() > 500)
                .collect(groupingBy(Usuario::getBanco));
        System.out.println("filter (> 500): " + porBancoSaldo500Mais2);

        // agrupamento em dois níveis: faixa → banco → estatísticas
        Map<String, Map<BancoEnum, DoubleSummaryStatistics>> agrupamentoDuplo = usuarios.stream()
                .collect(groupingBy(u -> {
                            if (u.getSaldo() > 1500) return "alto";
                            if (u.getSaldo() > 1000) return "médio";
                            return "baixo";
                        },
                        groupingBy(Usuario::getBanco,
                                Collectors.summarizingDouble(Usuario::getSaldo))));

        // navegação segura: qualquer nível ausente vira Optional vazio, sem NPE
        Optional.ofNullable(agrupamentoDuplo.get("alto"))
                .map(b -> b.get(BancoEnum.ITAU))
                .map(DoubleSummaryStatistics::getMax)
                .ifPresent(max -> System.out.println("Maior saldo alto no Itaú: " + max));
    }
}
