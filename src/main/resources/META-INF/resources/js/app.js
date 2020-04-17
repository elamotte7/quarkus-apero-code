/*global Vue, todoStorage */

(function (exports) {

    'use strict';

    var filters = {
        all: function (todos) {
            return todos;
        },
        active: function (todos) {
            return todos.filter(function (todo) {
                return !todo.tasted;
            });
        },
        tasted: function (todos) {
            return todos.filter(function (todo) {
                return todo.tasted;
            });
        }
    };

    exports.app = new Vue({

        // the root element that will be compiled
        el: '.todoapp',

        // app initial state
        data: {
            todos: [],
            newTodo: '',
            newColor: 'Yellow',
            editedTodo: null,
            visibility: 'all'
        },

        computed: {
            filteredTodos: function () {
                return filters[this.visibility](this.todos);
            },
            remaining: function () {
                return filters.active(this.todos).length;
            },
            allDone: {
                get: function () {
                    return this.remaining === 0;
                },
                set: function (value) {
                    this.todos.forEach(function (todo) {
                        todo.tasted = value;
                    });
                }
            }
        },

        methods: {

            pluralize: function (word, count) {
                return word + (count === 1 ? '' : 's');
            },

            addTodo: async function () {
                var value = this.newTodo && this.newTodo.trim();
                if (!value) {
                    return;
                }
                var color = this.newColor
                const item = await todoStorage.add({
                    name : value,
                    color: color,
                    order: this.todos.length + 1,
                    tasted: false
                });
                this.todos.push(item);
                this.newTodo = '';
            },

            removeTodo: async function (todo) {
                await todoStorage.delete(todo);
                await this.reload();
            },

            toggleTodo: function (todo) {
                todo.tasted = ! todo.tasted;
                todoStorage.save(todo);
            },

            editTodo: async function (todo) {
                this.beforeEditCache = todo.name;
                this.editedTodo = todo;
            },

            doneEdit: function (todo) {
                if (!this.editedTodo) {
                    return;
                }
                this.editedTodo = null;
                todo.name = todo.name.trim();
                if (!todo.name) {
                    this.removeTodo(todo);
                } else {
                    todoStorage.save(todo);
                }
            },

            cancelEdit: function (todo) {
                this.editedTodo = null;
                todo.name = this.beforeEditCache;
            },

            removeCompleted: async function () {
                await todoStorage.deleteCompleted();
                await this.reload();
            },

            reload: async function () {
                const data = await todoStorage.fetch();
                app.todos = data;
            }
        },

        // a custom directive to wait for the DOM to be updated
        // before focusing on the input field.
        // http://vuejs.org/guide/custom-directive.html
        directives: {
            'todo-focus': function (el, binding) {
                if (binding.value) {
                    el.focus();
                }
            }
        },

        mounted : async function() {
            this.reload();
        }
    });

})(window);