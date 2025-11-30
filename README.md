# Trivus Bank

Landing page do Trivus Bank - banco digital educativo que ensina enquanto você usa.

## Sobre o projeto

Criei esse site como parte do trabalho da faculdade (FIAP). A ideia do Trivus Bank é ser um banco diferente, que não só oferece serviços financeiros mas também ensina as pessoas a usarem. Pensei principalmente nos idosos e quem tem dificuldade com apps de banco.

O nome vem de "três virtudes": Segurança, Simplicidade e Transparência.

## Stack

Usei tecnologias básicas mesmo, mantendo simples:

- HTML5 e CSS3
- Bootstrap 5.3 (via CDN)
- JavaScript pra dark mode
- Um vídeo de background com triângulos animados

## Como ficou organizado

```
projeto/
├── index.html
├── README.md
└── assets/
    ├── css/
    │   └── style.css
    ├── js/
    │   └── script.js
    ├── images/
    │   ├── logo-trivus.svg
    │   └── (outras imagens do carousel)
    ├── videos/
        └── video triagulos bg.mp4
    
```

## O que tem no site

### Navegação
Menu com dropdown que funciona tanto hover quanto click. No mobile vira aquele menu sanduíche. Botei uns efeitos de transição suaves.

### Carousel
Fiz um carousel com 6 slides mostrando:
- O problema (idosos perdidos com apps)
- Nossa solução (banco educativo)
- O que oferecemos
- Tutoriais e educação financeira
- Nosso diferencial com IA

Configurei pra trocar automaticamente a cada 7 segundos.

### Dark Mode
Botão com emoji de lua/sol que troca o tema. É só JavaScript puro mesmo, usando classList.toggle. Ficou bem legal.

### Responsividade
Testei no celular e ficou legal. O texto do carousel diminui automaticamente pra não cobrir a imagem toda no mobile.

## Esquema de cores

Escolhi um verde escuro como cor principal:
- `#00563F` - verde principal para seguir a ideia do protótipo igual na fase anterior
- `#00cf87` - verde mais claro pros destaques
- `#003d2d` - verde mais escuro no hover

## Alguns detalhes técnicos

- Fontes aumentadas em 50% do padrão (ficou mais fácil de ler)
- Border radius de 50px nos botões
- Transição de 0.6s nos dropdowns
- Cards com hover effect (sobem um pouco)
- Overlay verde transparente no carousel (rgba com 0.95 de opacidade)

---

Projeto acadêmico - FIAP 2025
