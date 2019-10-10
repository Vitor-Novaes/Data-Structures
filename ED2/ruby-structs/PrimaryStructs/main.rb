# frozen_string_literal: true

# Vitor Novaes
# Structs ED2 : Primary Structs Review v.0.0.1
# UECE 08/2019
require File.expand_path('views/ui_sheel_menu')
require 'benchmark'
require 'benchmark/ips'
require 'benchmark/memory'

choice = print_main_menu()

case choice
  when :simple_list
    print_simple_list_menu()
  when :dup_list
    p 'Not Found'
    # print_simple_list_menu()
  when :queue_list
    p 'Not Found'
    # print_simple_list_menu()
  when :stack_list
    p 'Not Found'
    # print_simple_list_menu()
end


n = 5000000
Benchmark.ips do |x|
  x.report('1') { for i in 1..n; a = "1"; end }
  x.report('2') { n.times do   ; a = "1"; end }
  x.report('3') { 1.upto(n) do ; a = "1"; end }
  x.compare!
end