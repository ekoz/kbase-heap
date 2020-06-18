package com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射;

import com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射.entity.Dept;
import com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射.entity.Dept.DeptBuilder;
import com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射.vo.DeptVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射.vo.DeptVO.DeptVOBuilder;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-17T14:08:43+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_201-2-redhat (Oracle Corporation)"
)
public class BeanMapperImpl implements BeanMapper {

    @Override
    public DeptVO entityToVO(Dept dept) {
        if ( dept == null ) {
            return null;
        }

        DeptVOBuilder deptVO = DeptVO.builder();

        deptVO.parentName( deptParentName( dept ) );
        if ( deptParentId( dept ) != null ) {
            deptVO.parentId( deptParentId( dept ) );
        }
        else {
            deptVO.parentId( "ROOT" );
        }
        deptVO.id( dept.getId() );
        deptVO.name( dept.getName() );

        return deptVO.build();
    }

    @Override
    public Dept voToEntity(DeptVO vo) {
        if ( vo == null ) {
            return null;
        }

        DeptBuilder dept = Dept.builder();

        dept.parent( deptVOToDept( vo ) );
        dept.id( vo.getId() );
        dept.name( vo.getName() );

        return dept.build();
    }

    private String deptParentName(Dept dept) {
        if ( dept == null ) {
            return null;
        }
        Dept parent = dept.getParent();
        if ( parent == null ) {
            return null;
        }
        String name = parent.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String deptParentId(Dept dept) {
        if ( dept == null ) {
            return null;
        }
        Dept parent = dept.getParent();
        if ( parent == null ) {
            return null;
        }
        String id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Dept deptVOToDept(DeptVO deptVO) {
        if ( deptVO == null ) {
            return null;
        }

        DeptBuilder dept = Dept.builder();

        dept.name( deptVO.getParentName() );
        dept.id( deptVO.getParentId() );

        return dept.build();
    }
}
