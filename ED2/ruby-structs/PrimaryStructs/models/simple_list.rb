# frozen_string_literal: true

require_relative File.expand_path('models/cells')
require File.expand_path('./views/ui_sheel_structs')

class SimpleList
  attr_accessor :size, :head, :last

  def initialize
    @size = 0
    @head = nil
    @last = nil
  end

  # Insert Elements on List -------------------------------------
    def insert(value)    
      self.head.nil? ? insert_empty_list(value) : insert_on_end(value)

      print "=> Result insertion: "
      print_simple_list(self.last)
    end

    def insert_empty_list(value)
      new_cell = SimpleCell.new(value)
      self.head = new_cell
      self.last = new_cell
      self.size += 1
    end

    def insert_on_init(value)
      new_cell = SimpleCell.new(value)
      self.head.successor = new_cell
      self.size += 1
    end

    def insert_on_end(value)
      new_cell = SimpleCell.new(value)
      new_cell.successor = self.last
      self.last = new_cell
      self.size += 1
    end
  ###

  # Remove -------------------------------------
    # def delete(target_cell, value)
    #   if target_cell.value == value
    #     target_cell = target_cell.next
    #     return      
    #   end

    #   while value = .value

    #   node = find_before(value)
    #   node.next = node.next.next
    # end
  
  # List Methods Search -------------------------------------

  def search_cell_by_value(value)
    return nil if self.last.nil?
    
    tail = self.last
    
    while true
      if tail.value == value
        return tail
      elsif tail.successor
        tail = tail.successor
      else
        return nil
      end
    end
  end



  # Print List -------------------------------------
    def print_struct
      puts "\n => Linked List: "
      puts "\t| List Size: #{self.size}"
      print "\t| List Elements: "
      print_simple_list(self.last)
    end


    
end
