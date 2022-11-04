" Turn on syntax highlighting
syntax on
" Turn on smart auto-indentation
set autoindent
set smartindent
filetype plugin indent on
" Turn on auto-backup before editing
set backup
" Use ~/.backup as the backup directory
set backupdir=~/.backup
" Replace all tab with spaces
set expandtab
" Wrap long line at reasonable places (only affect 
" display, not content)
set wrap
set linebreak
" Make vim less like vi
set nocompatible
" Make default indent at 2 spaces
set shiftwidth=2
" Set tab stop to 2
set tabstop=2

" uncomment to turn on line number
set number
colorscheme zellner
" vim is optimized for keyboard-only, but if you insist on using mouse,
" uncomment the following
set mouse+=a

" uncomment the following to show ruler (line,colum position)
set ruler 

" some advanced options requested by students.  Use them only if
" you know what you are doing (I don't know what they do!)
" set hidden 
" set wildmenu 
" set showcmd 
" set hlsearch 
" set confirm 
" set visualbell 
" set ignorecase 
" set smartcase

" The following should give 16 colors on old Windows machine
"set t_AB=[%?%p1%{8}%<%t%p1%{40}%+%e%p1%{92}%+%;%dm
"set t_AF=[%?%p1%{8}%<%t%p1%{30}%+%e%p1%{82}%+%;%dm
"set t_Co=16

" The following change the backspace on old Windows machine
set t_kb=

" For students who are used to using the psvm/sout/sop abbreviation, you can
" uncomment the following.
"
" abbr psvm public static void main(String[] args){<CR>}<esc>O
" abbr sout System.out.println("");<esc>2hi
" abbr sop System.out.print("");<esc>2hi

"" PLUGINS
" vim-rainbow
let g:rainbow_active = 1

" For lightline
set laststatus=2
let g:lightline = { 'colorscheme': 'wombat' }

