package com.example.demo.Repository.book;

import com.example.demo.entity.Book;
import com.example.demo.entity.QBook;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class BookRepoImpl implements BookRepoCustom{

    private EntityManager em;

    public BookRepoImpl(EntityManager em) {

        this.em = em;
    }

    @Override
    public Page<Book> findAllBookAdvanced(Predicate predicate, Pageable pageable){
        JPAQuery<Book> queryFactory = new JPAQuery<>(em);
        QBook book = QBook.book;
        PathBuilder<Book> entityPath = new PathBuilder<>(Book.class, "book");
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder<Object> path = entityPath.get(order.getProperty());
            queryFactory.orderBy(new OrderSpecifier(Order.valueOf(order.getDirection().name()), path));
        }

        List<Book> books = queryFactory
                .select(book)
                .from(book)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .where(predicate)
                .fetch();

        return new PageImpl<>(books);
    }
}
