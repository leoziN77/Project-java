package com.example.controller;

import com.example.service.LibraryService;
import com.example.model.Book;

import javax.annotation.PostConstruct;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class LibraryController {
    private final LibraryService libraryService;
    private final Scanner scanner;

    
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println();
        System.out.println("Bem-vindo à Biblioteca");
        System.out.print("1. Cadastrar Livro: ");
        System.out.println();
        System.out.print("2. Listar Livros: ");
        System.out.println();
        System.out.print("3. Emprestar Livro: ");
        System.out.println();
        System.out.print("4. Devolver Livro: ");
        System.out.println();
        System.out.print("0. Sair: ");
        System.out.println();
        System.out.print("Escolha uma opção: ");
    }

    public void processUserInput() {
        int option;
        do {
            showMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                    System.out.println();
                    System.out.print("Digite o título do livro:");
                    String title = scanner.nextLine();
                    System.out.print("Digite o autor do livro:");
                    String author = scanner.nextLine();
                    libraryService.addBook(title, author);
                    System.out.println();
                    System.out.println("Livro cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Lista de Livros:");
                    libraryService.getAllBooks().forEach(book -> System.out.println(book.getTitle() + " - " + book.getAuthor()));
                    break;
                case 3:
                    System.out.println();
                    System.out.print("Digite o título do livro a ser emprestado:");
                    String loanTitle = scanner.nextLine();
                    Book loanedBook = libraryService.borrowBook(loanTitle);
                    if (loanedBook != null) {
                        System.out.println();
                        System.out.println("Livro emprestado com sucesso!");
                    } else {
                        System.out.println();
                        System.out.println("Livro não encontrado ou já emprestado.");
                    }
                    break;
                case 4:
                    System.out.println();
                    System.out.print("Digite o título do livro a ser devolvido:");
                    String returnTitle = scanner.nextLine();
                    boolean returned = libraryService.returnBook(returnTitle);
                    if (returned) {
                        System.out.println();
                        System.out.println("Livro devolvido com sucesso!");
                    } else {
                        System.out.println();
                        System.out.println("Livro não encontrado ou não foi emprestado.");
                    }
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (option != 0);
    }
    

    @PostConstruct
    public void init() {
        processUserInput();
    }
}
