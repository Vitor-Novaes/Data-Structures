# frozen_string_literal: true

require File.expand_path('./models/simple_list')

def print_main_menu
  system('clear')
  puts "\n> Program Review ED2 : Structs Primary in Ruby 2.6.3. by VN\n"
  puts "1 - Simple Linked List (Verified)"
  puts "2 - Dulp Linked List (miss)"
  puts "3 - Queue (miss)"
  puts "4 - Stack (miss)"  
  choices = { simple_list: '1' , dup_list: '2', queue_list: '3', stack_list: '4' }
  choice = gets.strip.to_s

  choices.key(choice)
end

# Simple Linked Lists
def print_simple_list_menu 
  list = SimpleList.new
  
  print "\n > Enter with quantity of elements = "
  size = gets.strip.to_i

  for i in 1..size 
    print " > Enter with quantity of Node[#{i}] = "
    value = gets.strip
    list.insert(value)
  end

  list.print_struct()

  loop do
    puts "\n> Simple Linked List in Ruby\n"
    puts "1 - Insert Elements"
    puts "2 - Search Element by Value"
    puts "3 - Delete Element by Value (miss)"
    puts "4 - Print List"
    puts "5 - Drop List (miss)"
    puts "6 - Exit"
    choices = { insert: '1' , search: '2', delete: '3', print: '4', drop: '5', exit: '6' }
    choice = gets.strip.to_s
    
    case choices.key(choice)
    when :insert 
      print "\n > Enter with quantity of Node [#{list.size + 1}] = "
      value = gets.strip
      list.insert(value)
      list.print_struct()
    when :search
      print "\n > Enter with quantity for search = "
      value = gets.strip
      result = list.search_cell_by_value(value)
      print_list_search_by_value(result)
      list.print_struct()
    when :delete
    when :print
      list.print_struct()
    when :drop
    when :exit
      break
    end
  end
end