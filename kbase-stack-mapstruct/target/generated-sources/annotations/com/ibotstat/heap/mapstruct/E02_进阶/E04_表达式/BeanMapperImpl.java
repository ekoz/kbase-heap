package com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式;

import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.entity.Dept;
import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.entity.Dept.DeptBuilder;
import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.vo.DeptVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-17T14:08:43+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_201-2-redhat (Oracle Corporation)"
)
public class BeanMapperImpl implements BeanMapper {

    @Override
    public Dept voToEntity(DeptVO vo) {
        if ( vo == null ) {
            return null;
        }

        DeptBuilder dept = Dept.builder();

        dept.id( vo.getId() );
        dept.name( vo.getName() );

        dept.parent( Dept.builder().id(vo.getParentId()).name(vo.getParentName()).build() );

        return dept.build();
    }
}
